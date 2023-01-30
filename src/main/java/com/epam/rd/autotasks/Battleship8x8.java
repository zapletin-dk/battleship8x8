package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {

        this.ships = ships;
    }

    public boolean shoot(String shot) {
        long tempMask = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        tempMask >>>= ((int) shot.charAt(0) - 65) + (Character.getNumericValue(shot.charAt(1)) - 1) * 8L;
        shots |= tempMask;
        return ships == (ships | tempMask);
    }


    public String state() {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
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
            res.append(sb.substring(i * 8, (i + 1) * 8)).append('\n');
        }
        res.append(sb.substring(56));
        return res.toString();
    }

    public static String longToBinaryString(long input) {
        return String.format("%64s", Long.toBinaryString(input)).replace(' ', '0');
    }

}