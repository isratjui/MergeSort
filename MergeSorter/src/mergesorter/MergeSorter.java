/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesorter;

/**
 *
 * @author JUI
 */
import java.util.*;
public class MergeSorter implements Runnable{


private int[] arr;
private int Size;
private int left;
private int right;
private int[] resultArr ;

public MergeSorter(int[] arr, int i, int j) {
    this.arr = arr;
    this.Size = arr.length;
    //this.resultArr = new int[j-i+1];
    this.left  = i;
    this.right = j;
}



@Override
public void run() {

    System.out.println("Starting new thread left :"+this.left+" right "+this.right);

    sort();

}

public static void main(String[] args) {
   //int arr[] ={3,6,4,2,1,10,7} ;
//  int arr=new int[6];
   //input from user
 Scanner s=new Scanner(System.in);

    System.out.println("enter number of elements");

    int n=s.nextInt();

    int arr[]=new int[n];

    System.out.println("enter elements");

    for(int i=0;i<arr.length;i++){//for reading array
        arr[i]=s.nextInt();

    }
    
   //String[] Array;

  
  String temp;
  
 // Scanner input=new Scanner(System.in);
// System.out.println("name number");
//int j=s.nextInt();
// Array=new String[j]; 
// for(int i=0;i<Array.length;i++){
//  System.out.println("Give me the name ");
 // Array[i]=s.next();
 // }
    

MergeSorter mr = new MergeSorter(arr,0,arr.length-1);

    Thread t = new Thread(mr);
    t.start();
    //mr.run();
    
    //MergeSorter mr1 = new MergeSorter(rr,0,arr.length-1);
    

try {
        t.join();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

for (int i :mr.resultArr)
        System.out.print(i+" ");
    //int res[]= mr.sort(0,arr.length-1);

}






private void sort() {

    if(left==right && left >=0 )
    {
         this.resultArr = new int[]{arr[left]};
         return;
    }
    if(left>right) return;

    int rightLimit = this.left+(right-left)/2;
    //int leftArr[] = sort( left,rightLimit );

    MergeSorter mleft = new MergeSorter(arr,left,rightLimit);
    Thread t1 = new Thread(mleft);
    t1.start();

     int leftlimit = 1 + rightLimit;            
     //int rightArr[] = sort(leftlimit , right);

     MergeSorter mright= new MergeSorter(arr,leftlimit,right);
     Thread t2 = new Thread(mright);
      t2.start();


       try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

      merge(mleft.resultArr,mright.resultArr);



}



private  int[] merge(int[] left, int[] right) {
     resultArr = new int[left.length+right.length];

    int r=0;
    int i=0;
    int j=0;
    while(i<left.length && j <right.length )
    {   
        if( i<left.length && j<right.length && left[i] < right[j] )
            resultArr[r++] = left[i++];

        else if( j<right.length && i<left.length && right[j] < left[i])
            resultArr[r++] = right[j++];
    }   


    while(i<left.length)
    {
        resultArr[r++] = left[i++];
    }        

    while(j<right.length)
    {
        resultArr[r++] = right[j++];
    }        

    return resultArr;
}



}
