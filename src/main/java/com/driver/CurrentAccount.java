package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000.0);

        if(balance < 5000) throw new Exception("Insufficient Balance");

        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag = false;

        for(int i=1; i<tradeLicenseId.length(); i++) {
            if(tradeLicenseId.charAt(i-1)==tradeLicenseId.charAt(i)) {
                flag = true;
                break;
            }
        }

        if(!flag) return;

        //function for rearrange
        int max = 0;
        int idx = 0;
        int[] freq = new int[26];
        for(int i=0; i<tradeLicenseId.length(); i++) {
            freq[tradeLicenseId.charAt(i)-'A']++;
            if(freq[tradeLicenseId.charAt(i)-'A'] > max) idx = i;
            max = Math.max(max, freq[tradeLicenseId.charAt(i)-'A']);
        }
        String ans = "";
        char[] arr = new char[tradeLicenseId.length()];
        int index = 0;
        if((tradeLicenseId.length()%2==0 && max <= tradeLicenseId.length()/2) || (tradeLicenseId.length()%2!=0 && max <= tradeLicenseId.length()/2 + 1)) {
            for(int i=0; i<26; i++) {

                while(freq[i] > 0) {
                    if(index >= tradeLicenseId.length()) index = 1;
                    arr[index] = (char)(65+i);
                    freq[i]--;
                    index += 2;
                }
            }
            tradeLicenseId = arr.toString();
            return;
        }
        //not possible
        throw new Exception("Valid License can not be generated");
    }

}
