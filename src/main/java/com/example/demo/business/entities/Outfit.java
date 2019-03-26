package com.example.demo.business.entities;


import java.util.Random;





public class Outfit {
        /*
            Write a program that will allow a robot to assemble 5 outfits.
            Use a random generator for each burrito option and build a list of
            10 burrito customizations:
            Rice: white, brown, no rice
            Meat: chicken, steak, carnidas, chorizo, sofritas, veggies
            Beans: pinto, black, no beans
            Salsa: mild, medium, hot, no salsa
            Veggies: lettuce, fajita veggies, no veggies*
        */



        public static void main(String[] args) {
            // Initialization of random numbers specific to burrito options

            Random a = new Random();
            Random b = new Random();
            Random j = new Random();
            Random s = new Random();
            Random t = new Random();



            // Int list of random variables specific to burrito options
            int ax =  a.nextInt(4);
            int bx =  b.nextInt(7);
            int jx =  j.nextInt(8);
            int sx =  s.nextInt(3);
            int tx =  t.nextInt(6);





            //String array of Options
            String[] tops = {"White Short-sleeve T-Shirt, ", "Grey Short-sleeve Sports Shirt, ", "Blue T-Shirt, ", "Blue long Sleeve T-shirt, ", "Blue Short-sleeve Science Shirt, ", "Grey Long Sleeve, "};
            String[] bottoms = {"Cargo khaki, ", "Jeans Blue, ", "Khaki Brown, ", "Snow Gray, ", "Shorts Blue, ", "Shorts Grey,", "Shorts Khaki"};
            String[] shoes = {"Black Tan Timberland", "Grey long Socks", "Nike-Green-Winter-Shoes", "Winter-Socks-Grey-White", "Winter-Socks-Grey-Yellow", "Nhan's All-weather Sandals", "Warm Midhigh Socks"};
            String[] jackets = {"Black Dark-Grey Jacket", "Black Grey Jacket", "Black Light-Grey Jacket", "Black Jacket", "Green Grey Jacket", "Northface Black Jacket", "Black Nike Windbreaker", "White Navy-Blue Windbreaker Columbia"};
            String[] accessories = {"Winter", "Spring", "Summer", "Fall"};

            for (int i = 1; i < 6; i++) {
                // int j = i + 1;
                System.out.println();
                System.out.print("Outfit "+i+": ");
                System.out.print(tops[t.nextInt(6)]);
                System.out.print(bottoms[b.nextInt(7)]+" " );
                System.out.print(shoes[s.nextInt(3)]+", ");
                System.out.print(jackets[j.nextInt(8)]+", ");
                System.out.print(accessories[a.nextInt(4)]+" ");
                System.out.println();
//                System.out.println();
                /* Tried to print an Array of Arrays, didn't like that one, next I'd attempt an array list of arrays, but the above code will do*/
                // System.out.println("Burrito "+ i + ": "+ rice[rx] +" "+ meat[mx]+"," + beans[bx]);

            }

        }

        //String burrito[] = {rice[rx], meat[mx], beans[bx], salsa[sx], veggies[vx]};

        /*+ ", "+beans[bx]+ ", "+salsa[sx]+", "+veggies[vx]*/


        // Print
/*
        System.out.println("Burrito ");
        for (int i = 0; i <10 ; i++) {
            System.out.println("Burrito " + i + rice[rx] +"," + meat[mx]);
                            /*+ ", "+beans[bx]+ ", "+salsa[sx]+", "+veggies[vx]*/
    }









