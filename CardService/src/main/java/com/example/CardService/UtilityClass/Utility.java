package com.example.CardService.UtilityClass;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utility {

    private static long lastTimestamp = System.currentTimeMillis();
    private static long counter = 0;

    public static synchronized String generateId() {
        long currentTime = System.currentTimeMillis();

        if (currentTime == lastTimestamp) {
            counter++;
        } else {
            lastTimestamp = currentTime;
            counter = 0;
        }

        return String.valueOf(currentTime * 1000 + counter);
    }

    public static String luhnGenerator(){
        String number="";
        int sum=0;
        Random r=new Random();
        for(int i=0;i<15;i++){
            int num=r.nextInt(10);
            if(i%2==0){
              num*=2;
              if(num>9){
              num-=9;
              }
              number=number+num;
              sum+=num;
            }else{
                number=number+num;
                sum+=num;
            }
        }
            System.out.println(sum);
            int rem=sum%10;
            if(rem!=0){
            number=number+(10-rem);
            sum+=(10-rem);
            System.out.println(sum);
            }
            return number;
  }

  public static String cvvGenerator(){
    int cvv=new Random().nextInt(900)+100;
    return String.valueOf(cvv);
  }

}