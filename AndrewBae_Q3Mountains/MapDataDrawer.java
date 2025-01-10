import java.util.*;
import java.io.*;
import java.awt.*;

public class MapDataDrawer
{
  public int[][] grid;

  public MapDataDrawer(String filename, int rows, int cols){  
      //initialize grid 
      //read the data from the file into the grid
      grid = new int[rows][cols];

      Scanner scanner;
      try {
          scanner = new Scanner(new File(filename));
          for (int i = 0; i < rows; i++)
          {
              for (int j = 0; j < cols; j++)
              {
                  if (scanner.hasNextInt())
                  {
                      grid[i][j] = scanner.nextInt(); //iterate through all of the rows and columns
                  }
              }
              
          }
          
      } 
      catch (FileNotFoundException e) {
          e.printStackTrace();
      }
  }
  
  /**
   * @return the min value in the entire grid
   */
  public int findMinValue(){
      int min = grid[0][0];
      for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
              if (grid[i][j] < min){
                  min = grid[i][j]; //replace min with current if the current value is smaller
              }
          }
      }
      return min;
  }
  
  /**
   * @return the max value in the entire grid
   */
  public int findMaxValue(){
      int max = grid[0][0];
      for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
              if (grid[i][j] > max){
                  max = grid[i][j]; //replace max with current if the current calue is bigger
              }
          }
      }
      return max;
  }
  
  /**
   * @param col the column of the grid to check
   * @return the index of the row with the lowest value in the given col for the grid
   */
  public int indexOfMinInCol(int col){
  
      int min = grid[0][col];
      int minRow = 0;
      for (int i = 1; i < grid.length; i++)
      {
          if (grid[i][col] < min)
          {
              min = grid[i][col]; //same as above but this time for columns
              minRow = i;
          }
      }
      return minRow;
  }
  
  /**
   * Draws the grid using the given Graphics object.
   * Colors should be grayscale values 0-255, scaled based on min/max values in grid
   */
  public void drawMap(Graphics g){
      int min = findMinValue();
      int max = findMaxValue();
      
      double scale = 255.0 / (max - min); //because the scale needs to be between 0-255
      
      int[][] greyscale = new int[grid.length][grid[0].length];
      
      for (int i = 0; i < grid.length; i++)
      {
          for (int j = 0; j < grid[0].length; j++)
          {
              greyscale[i][j] = (int) ((grid[i][j] - min) * scale); //normalize the grid value to a greyscale range 0-255
          }
      }
      
      for (int i = 0; i < greyscale.length; i++)
      {
          for (int j = 0; j < greyscale[0].length; j++)
          {
              int value = greyscale[i][j]; //get the greyscale value for the current position
              g.setColor(new Color(value, value, value)); //set the color to a shade of grey determined by greyscale value
              g.fillRect(j, i, 1, 1); //draw a 1x1 pixel at the current position
          }
      }
  }

   /**
   * Find a path from West-to-East starting at given row.
   * Choose a forward step out of 3 possible forward locations, using greedy method described in assignment.
   * @return the total change in elevation traveled from West-to-East
   */
  public int drawLowestElevPath(Graphics g, int row){
      int max = findMaxValue();
      int totalChange = 0; //to keep track of the total elevation change
      
      for (int j = 0; j < grid[0].length - 1; j++)
      {
          g.fillRect(j, row, 1, 1);
          int fwd = grid[row][j + 1]; //forward cell in the current row
          int fwd_N = -1; //forward cell in the row above and initialize to -1 if invalid
          int fwd_S = -1; //forward cell in the row below
          
          if (row != 0) //check if a forward north cell exists
          {
              fwd_N = grid[row - 1][j + 1];
          }
          
          if (row != grid.length - 1) //check if a forward south cell exists
          {
              fwd_S = grid[row + 1][j + 1];
          }
          
          int current_fwd_dif = Math.abs(grid[row][j] - fwd); //find the elevation change for moving forward
          int current_fwdN_dif = max + 1; //initialize elevation changes for north and south to a high value
          int current_fwdS_dif = max + 1;
          if (fwd_N > -1) //update the elevation change for moving south if there is a north cell
          {
              current_fwdN_dif = Math.abs(grid[row][j] - fwd_N);
          }
          if (fwd_S > -1) //same for south
          {
              current_fwdS_dif = Math.abs(grid[row][j] - fwd_S);
          }
                
          int least = current_fwd_dif;
          
          if (current_fwd_dif > current_fwdN_dif)
          {
              if (current_fwdN_dif > current_fwdS_dif)
              {
                  least = current_fwdS_dif; //choose south
                  row++; //move to the row below
              }
              else
              {
                  least = current_fwdN_dif; //choose north
                  row--; //move to the row above
              }
          }
          else
          {
              if (current_fwd_dif > current_fwdS_dif)
              {
                  least = current_fwdS_dif; //choose south
                  row++; //move to the row below
              }
              else
              {
                  least = current_fwd_dif; //stay in the current row
              }
          }
          
          totalChange += least; //add the least elevation change to the total change
          
      }
      
      return totalChange;
  }
  
  /**
   * @return the index of the starting row for the lowest-elevation-change path in the entire grid.
   */
  public int indexOfLowestElevPath(Graphics g){
     int least = drawLowestElevPath(g, 0);
     int index = 0;
             
     for (int i = 1; i < grid.length; i++) //iterate through the remaining rows of the grid starting from row 2
     {
         int change = drawLowestElevPath(g, i); //calculate the total elevation change for the current row
         if (change < least)
         {
             least = change; //update the smallest elevation change
             index = i; //update the index to the current row
         }
     }

     return index;
  }
}