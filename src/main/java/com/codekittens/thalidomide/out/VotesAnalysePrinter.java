package com.codekittens.thalidomide.out;

import com.codekittens.thalidomide.model.VoteAnalyseResult;
import com.codekittens.thalidomide.model.WrappedVoteResponse;
import com.codekittens.thalidomide.model.trasnport.Vote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VotesAnalysePrinter<T extends WrappedVoteResponse> extends Printer<T> {

    private VoteAnalyseResult result;

    public VotesAnalysePrinter() {
        result = new VoteAnalyseResult();
    }

    @Override
    public void print(T input) {
        result = new VoteAnalyseResult();

    }

    @Override
    public void print(List<T> input) {

        input.forEach((WrappedVoteResponse resp) -> {
            if (resp.getVoteResponse().getPros() != null) {
                resp.getVoteResponse().getPros().forEach((Vote vote) -> result.addVote(vote));
            }

            if (resp.getVoteResponse().getCons() != null) {
                resp.getVoteResponse().getCons().forEach((Vote vote) -> result.addVote(vote));
            }
        });

        List<VoteAnalyseResult.UserVoteResult> positiveVotes = new ArrayList<>();
        positiveVotes.addAll(result.getPositiveVotes().values());

        Comparator<VoteAnalyseResult.UserVoteResult> comp =
                (VoteAnalyseResult.UserVoteResult r1, VoteAnalyseResult.UserVoteResult r2) -> (r1.compareTo(r2));

        positiveVotes.sort(comp);

        positiveVotes.subList(0, 100).forEach((VoteAnalyseResult.UserVoteResult re) -> {
            System.out.println("User: " + re.getUser().getLogin());
            System.out.println("Total positive votes: + " + re.getVotes().size());
            System.out.println("Total positive rating: + " + re.getResult());
            System.out.println();
        });
    }

}
