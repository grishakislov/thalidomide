package com.codekittens.thalidomide.out;

import com.codekittens.thalidomide.model.KarmaResponse;
import com.codekittens.thalidomide.model.Vote;

public class KarmaPrinter<T extends KarmaResponse> extends Printer<T> {

    private static final int TAB_SIZE = 30;

    @Override
    public void print(T input) {
        System.out.println("Current karma: " + input.getKarma());
        System.out.println("Listing offset: " + input.getOffset());
        System.out.println();

        int maxColumnSize = Math.max(input.getCons().size(), input.getPros().size());
        boolean conExist;
        boolean proExist;
        String currentRow = "";
        for (int i = 0; i < maxColumnSize; i++) {

            conExist = input.getCons().size() > i;
            proExist = input.getPros().size() > i;
            if (conExist) {
                Vote currentCon = input.getCons().get(i);
                currentRow += voteToString(currentCon);
            }
            currentRow += "|  ";
            if (proExist) {
                Vote currentPro = input.getPros().get(i);
                currentRow += voteToString(currentPro);
            }
            if (conExist || proExist) {
                System.out.println(currentRow);
            }
            currentRow = "";
        }
    }

    private String voteToString(Vote vote) {
        String result = "";
        result += vote.getUser().getLogin();
        result += "(" + vote.getVote() + ")";

        int remains = TAB_SIZE - result.length();
        if (remains > 0) {
            for (int i = 0; i < remains; i++) {
                result += " ";
            }
        }
        return result;
    }
}
