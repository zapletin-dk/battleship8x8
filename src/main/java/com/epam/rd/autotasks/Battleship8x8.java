package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        long shootsMask = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        shootsMask >>>= ((int) shot.charAt(0) - 65) + (Character.getNumericValue(shot.charAt(1)) - 1) * 8L;
        shots |= shootsMask;
        return ships == (ships | shootsMask);
    }

    public String state() {
        StringBuilder sb = new StringBuilder();
        StringBuilder state = new StringBuilder();
        for (int i = 0; i< 64 ; i++){
            if (longToBinaryString(shots).charAt(i) == '1' && longToBinaryString(ships).charAt(i) == '1'){
                sb.append('☒');
            } else if (longToBinaryString(shots).charAt(i) == '1' && longToBinaryString(ships).charAt(i) == '0'){
                sb.append('×');
            } else if (longToBinaryString(shots).charAt(i) == '0' && longToBinaryString(ships).charAt(i) == '1'){
                sb.append('☐');
            } else if (longToBinaryString(shots).charAt(i) == '0' && longToBinaryString(ships).charAt(i) == '0'){
                sb.append('.');
            }
        }
        for (int i = 0; i < 7; i++) {
            state.append(sb.substring(i * 8, (i + 1) * 8)).append('\n');
        }
        state.append(sb.substring(56));
        return state.toString();
    }

    public static String longToBinaryString(long input) {
        return String.format("%64s", Long.toBinaryString(input)).replace(' ', '0');
    }

}