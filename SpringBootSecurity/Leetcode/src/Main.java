import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
int [] arr={1,1,2,2,2,4,3,3,3,3};
//removeduplicat(arr);
        //removegivenelement(arr,1);
       // System.out.println(binarysearch(arr,6));
//        int [] a=arr(arr);
//        System.out.println(Arrays.toString(a));
      //singlenumber(arr);
        //kadanisalgorith();
        //System.out.println(majorityelement(arr));
        singleelement(arr);
    }
    public static void removeduplicat(int [] nums){
        int index=1;
        for(int i=2;i<nums.length;i++){
            if(nums[i-1] <nums[i]){
                nums[index]=nums[i];
                index++;
            }
        }

        System.out.println(Arrays.toString(nums));
    }
   public static void removegivenelement(int [ ] arr,int val){
        int index=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=val){
                arr[index++]=arr[i];
            }
        }
//        while(index <arr.length){
//            arr[index++]=0;
//        }
       System.out.println(Arrays.toString(arr));
       System.out.println(index-1);
   }
   // binary search [1,2,3,4] target=3 answer would be 2 if ther is no any targed value what you will do
    public static int binarysearch(int [] arr,int target){
        int left=0;
        int right=arr.length-1;
        while(left<=right){
            int mid=(left +right)/2;
            if(arr[mid]==target){
               return  mid;
            }else if(arr[mid] < target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return  left;
    }
    public static int [] arr(int [] arr){
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]+1 != 10){
                arr[i]=arr[i]+1;
                return arr;
            }
            arr[i]=0;

        }
        int [] nes=new int[arr.length+1];
        nes[0]=1;
        return nes;
    }
    public static int maj(int [] arr){
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }
    public static void singlenumber(int arr[]){
        int [] hash=new int[maj(arr)+1];
        for(int i=0;i<arr.length;i++){
            hash[arr[i]]++;
        }
        for(int i=0;i<hash.length;i++){
            if(hash[i]==1){
                System.out.println(i);
                break;
            }
        }

    }
    public static void kadanisalgorith(){
        int [] arr={-2,7,-3,4};
        int cs=0;
        int ms=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int t=cs+arr[i];
            if(t<arr[i]){
                cs=arr[i];
            }else{
                cs=t;
            }
            if(ms < cs){
                ms=cs;
            }

        }
        System.out.println(ms);
    }
     public static int majorityelement(int [] arr){
        Arrays.sort(arr);
        int num=arr.length;
        return arr[num/2];
     }
     public static void singleelement(int [] arr){
         int res=0;
         for(int i=0;i<arr.length;i++){
             res ^= arr[i];
         }
         System.out.println(res);
     }
}