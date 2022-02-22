package com.company;

class Triangle implements Figure{

    public void print(int size) {
        for(int i = 1; i <= size; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                System.out.print("0 ");
            }
            System.out.print("\n");
        }
    }
}
