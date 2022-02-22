package com.company;

class Square implements Figure{

    public void print(int size) {
        for(int i = 1; i <= size; i++)
        {
            for(int j = 1; j <= size; j++)
            {
                System.out.print("0 ");
            }
            System.out.print("\n");
        }
    }
}
