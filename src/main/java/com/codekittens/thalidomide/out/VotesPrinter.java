package com.codekittens.thalidomide.out;

import com.codekittens.thalidomide.model.trasnport.User;
import com.codekittens.thalidomide.model.trasnport.Vote;
import com.codekittens.thalidomide.model.WrappedVoteResponse;

import java.io.PrintWriter;
import java.util.*;

public class VotesPrinter<T extends WrappedVoteResponse> extends Printer<T> {

    private class Result {
        private int numPositiveVotes;
        private int numNegativeVotes;

        private int totalPositiveRating;
        private int totalNegativeRating;

        private User user;

        private Result() {
            numNegativeVotes = numPositiveVotes = totalNegativeRating = totalPositiveRating = 0;
        }
    }

    private Map<User, Result> allVotesByUser;

    private FileHelper fileHelper;

    public VotesPrinter() {
        allVotesByUser = new HashMap<>();

    }

    @Override
    public void print(T input) {
        try {
            fileHelper.println("Votes for comment " + input.getCommentId());
            if (input.getVoteResponse().getCons() != null) {
                input.getVoteResponse().getCons().forEach((Vote vote) -> addVote(vote));
            }
            if (input.getVoteResponse().getPros() != null) {
                input.getVoteResponse().getPros().forEach((Vote vote) -> addVote(vote));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addVote(Vote vote) {
        Result result = allVotesByUser.get(vote.getUser());

        if (result == null) {
            result = new Result();
        }

        boolean neg = vote.getVote() < 0;

        if (neg) {
            result.numNegativeVotes++;
            result.totalNegativeRating+=Math.abs(vote.getVote());
        } else {
            result.numPositiveVotes++;
            result.totalPositiveRating+=vote.getVote();
        }

        result.user = vote.getUser();
        allVotesByUser.put(vote.getUser(), result);
    }

    @Override
    public void print(List<T> input) {
        try {
            fileHelper = new FileHelper("log.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        input.forEach((T value) -> print(value));

        List<Result> results = new ArrayList<>();
        results.addAll(allVotesByUser.values());

        results.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.numPositiveVotes - o1.numPositiveVotes;
            }
        });

        printResults(results);
        fileHelper.close();
    }

    private void printResults(List<Result> results) {
        fileHelper.println();
        fileHelper.println();
        fileHelper.println("Results:");
        fileHelper.println();
        fileHelper.println("Positive votes:");
        results.forEach((Result result) -> {
            fileHelper.println("  User: " + result.user.getLogin() + ", id: " + result.user.getId());
            fileHelper.println("  Positive votes:        " + result.numPositiveVotes);
            fileHelper.println("  Total positive rating: " + result.totalPositiveRating);
            fileHelper.println("  Negative votes:        " + result.numNegativeVotes);
            fileHelper.println("  Total negative rating: " + result.totalPositiveRating);
            fileHelper.println("----------------");
            fileHelper.println();
        });
    }
}
