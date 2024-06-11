import java.util.*;
public class MergeSort{
    public static void main(String args[]){
        int[] arr={5,4,3,2,1};
        int n=arr.length-1;
        System.out.println(Arrays.toString(arr));
        ms(arr,0,n);
        System.out.println(Arrays.toString(arr));
    }
    public static void ms(int[] arr,int low,int high){
        if (low==high)  return;
        int mid=low+(high-low)/2;
        ms(arr,low,mid);
        ms(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    public static void merge(int[] arr,int low,int mid,int high){
        int p1=low,p2=mid+1,k=0;
        int[] nums=new int[high-low+1];
        while(p1<=mid && p2<=high){
            if(arr[p1]<arr[p2]){
                nums[k++]=arr[p1++];
            }
            else if(arr[p1]>arr[p2]){
                nums[k++]=arr[p2++];
            }
        }
        while(p1<=mid){
            nums[k++]=arr[p1++];
        }
        while(p2<=high){
            nums[k++]=arr[p2++];
        }
        for(int i=0;i<nums.length;i++){
            arr[i+low]=nums[i];
        }
    }
    
}
