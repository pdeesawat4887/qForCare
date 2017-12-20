package com.example.pacharapoldeesawat.demohospital;


import android.util.Log;




public class CheckTimeBox {


    private static int timebox;


    public static int checkTimeBox(int hour, int min){



        switch (hour) {
<<<<<<< HEAD
            case 13: timebox = (min < 30) ? 1 : 2;
                break;
            case 14: timebox = (min < 30) ? 3 : 4;
                break;
            case 15: timebox = (min < 30) ? 5 : 6;
                break;
            case 16: timebox = (min < 30) ? 7 : 8;
                break;
            case 17: timebox = (min < 30) ? 9 : 10;
                break;
            default:   timebox = 0;
=======

            case 21: timebox = (min < 30) ? 1 : 2;

                break;

            case 22: timebox = (min < 30) ? 3 : 4;

                break;

            case 23: timebox = (min < 30) ? 5 : 6;

                break;

            case 0: timebox = (min < 30) ? 7 : 8;

                break;

            case 1: timebox = (min < 30) ? 9 : 10;

                break;

            default:   timebox = 0;

>>>>>>> 33b4441599b4d41c755ed9fda62008a0c42179a2
                break;

        }

        return timebox;


    }

}
