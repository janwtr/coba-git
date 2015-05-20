/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lat1;

/**
 *
 * @author jan
 */
public class Coba {
    public static void main(String[] args) {
        int sum = 0;
        int nums[][][] = new int[3][5][4];        
        for(int i = 0; i < 3; i++)
            for(int j=0; j < 5; j++)
                for(int k=0; k < 4; k++)
                    nums[i][j][k] = (i+1)*(j+1);  
        
        for(int x[][] : nums) {
            for(int y[] : x) {
                for(int z : y) {
                    System.out.println("Value is: " + z);
                    sum += z;
                }
            }
        }
        System.out.println("Summation: " + sum);

    }
}
