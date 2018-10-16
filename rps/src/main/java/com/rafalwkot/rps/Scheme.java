package com.rafalwkot.rps;

import java.util.Objects;
import java.util.Set;

public class Scheme {
    private final Move move;
    private final Set<Move> winWith;
    private final Set<Move> loseWith;

    public Scheme(Move move, Set<Move> winWith, Set<Move> loseWith) {
        this.move = move;
        this.winWith = winWith;
        this.loseWith = loseWith;
    }

    public Move getMove() {
        return move;
    }

    public Set<Move> getWinWith() {
        return winWith;
    }

    public Set<Move> getLoseWith() {
        return loseWith;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheme scheme = (Scheme) o;
        return move == scheme.move &&
                Objects.equals(winWith, scheme.winWith) &&
                Objects.equals(loseWith, scheme.loseWith);
    }

    @Override
    public int hashCode() {

        return Objects.hash(move, winWith, loseWith);
    }
}
