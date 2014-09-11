package com.codekittens.thalidomide.out;

import com.codekittens.thalidomide.model.User;
import com.codekittens.thalidomide.model.Vote;
import com.codekittens.thalidomide.model.VoteResponse;
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

    private PrintWriter writer;

    public VotesPrinter() {
        allVotesByUser = new HashMap<>();

    }

    @Override
    public void print(T input) {
        try {
            writer.println("Votes for comment " + input.getCommentId());
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
            writer = new PrintWriter("log.txt", "UTF-8");
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
        writer.close();
    }

    private void printResults(List<Result> results) {
        writer.println();
        writer.println();
        writer.println("Results:");
        writer.println();
        writer.println("Positive votes:");
        results.forEach((Result result) -> {
            writer.println("  User: " + result.user.getLogin() + ", id: " + result.user.getId());
            writer.println("  Positive votes:        " + result.numPositiveVotes);
            writer.println("  Total positive rating: " + result.totalPositiveRating);
            writer.println("  Negative votes:        " + result.numNegativeVotes);
            writer.println("  Total negative rating: " + result.totalPositiveRating);
            writer.println("----------------");
            writer.println();
        });
    }
}
