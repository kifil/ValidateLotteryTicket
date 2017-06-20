/**
 * Created by dwilson on 6/19/2017.
 */
public class Lottery {

    public void main(String[] args){
        System.out.println(isValidLotteryNumber(args[0]));
    }

    // helper with some simple sanity checks to avoid huge inputs
    boolean isValidLotteryNumber(String lotteryNumber){
        if(lotteryNumber.length() < 7 || lotteryNumber.length() > 14){
            return false;
        }
        return isValidLotteryNumber(lotteryNumber,0);
    }

    boolean isValidLotteryNumber(String lotteryNumber, int validNumbers){
        boolean isValid = false;
        if(lotteryNumber == "" && validNumbers == 7){
            return true;
        }
        else if(lotteryNumber == "" && validNumbers < 7){
            return false;
        }
        else if(validNumbers > 7){
            return false;
        }

        //try 2 digit number first
        String firstTwoNumbersString = getRemainingString(lotteryNumber, 0,2);
        if(isNumberValid(firstTwoNumbersString)){
            validNumbers++;
            String remainingNumbers = getRemainingString(lotteryNumber, 2,lotteryNumber.length());
            isValid = isValidLotteryNumber(remainingNumbers, validNumbers);
        }
        //try single number if double digit number not valid (either invalid number or total length was off)
        else if(!isValid){

            String firstCharacterString = getRemainingString(lotteryNumber, 0,1);
            if(!isNumberValid(firstCharacterString)){
                return false;
            }
            validNumbers++;
            String remainingNumbers = getRemainingString(lotteryNumber, 1,lotteryNumber.length());
            isValid = isValidLotteryNumber(remainingNumbers, validNumbers);
        }

        return isValid;
    }

    //just get an empty string if we are trying to get too many characters
    String getRemainingString(String input, int startIndex, int endIndex){
        try{
            input.substring(startIndex,endIndex);
        }
        catch(Exception e){
            return "";
        }
    }

    boolean isNumberValid(String numberString){
        Integer number = null;
        try{
            Integer.parseInt(numberString);
            if(number >= 1 && number <= 59){
                return true;
            }
        }
        catch(Exception e){}

        return false;
    }

}
