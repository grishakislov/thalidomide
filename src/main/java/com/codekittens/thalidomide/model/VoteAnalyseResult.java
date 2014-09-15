package com.codekittens.thalidomide.model;

import com.codekittens.thalidomide.model.trasnport.User;
import com.codekittens.thalidomide.model.trasnport.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteAnalyseResult {

    private Map<User, UserVoteResult> positiveVotes;
    private Map<User, UserVoteResult> negativeVotes;
    private Map<User, UserVoteResult> totalVotes;


    public class UserVoteResult implements Comparable<UserVoteResult> {

        private User user;
        private ResultType type;
        private List<Vote> votes;
        private int result;

        public UserVoteResult() {
            votes = new ArrayList<>();
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public ResultType getType() {
            return type;
        }

        public void setType(ResultType type) {
            this.type = type;
        }

        public List<Vote> getVotes() {
            return votes;
        }

        public void setVotes(List<Vote> votes) {
            this.votes = votes;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        @Override
        public int compareTo(UserVoteResult o) {
            return (this.getVotes().size() > o.getVotes().size() ? -1 :
                   (this.getVotes().size() < o.getVotes().size()) ? 1 : 0);
        }
    }

    public enum ResultType {
        POSITIVE, NEGATIVE, TOTAL;
    }

    public VoteAnalyseResult() {
        positiveVotes = new HashMap<>();
        negativeVotes = new HashMap<>();
        totalVotes = new HashMap<>();
    }

    public void addVote(Vote vote) {
        boolean positive = vote.getVote() > 0;

        if (positive) {
            addOrUpdate(vote, positiveVotes, ResultType.POSITIVE);
        } else {
            addOrUpdate(vote, negativeVotes, ResultType.NEGATIVE);
        }

        addOrUpdate(vote, totalVotes, ResultType.TOTAL);
    }

    private void addOrUpdate(Vote vote, Map<User, UserVoteResult> target, ResultType resultType) {
        User user = vote.getUser();
        boolean exists = target.containsKey(user);
        UserVoteResult userVoteResult;
        if (exists) {
            userVoteResult = target.get(user);
            userVoteResult.getVotes().add(vote);
            userVoteResult.setResult(userVoteResult.getResult() + vote.getVote());
        } else {
            userVoteResult = new UserVoteResult();
            userVoteResult.setUser(user);
            userVoteResult.setType(resultType);
            userVoteResult.getVotes().add(vote);
            userVoteResult.setResult(vote.getVote());
            target.put(user, userVoteResult);
        }

    }

    public Map<User, UserVoteResult> getPositiveVotes() {
        return positiveVotes;
    }

    public Map<User, UserVoteResult> getNegativeVotes() {
        return negativeVotes;
    }

    public Map<User, UserVoteResult> getTotalVotes() {
        return totalVotes;
    }

}
