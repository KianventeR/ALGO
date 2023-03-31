package Algorithm;

public class test {
    public static void main(String[] args){

        int[] bursts = {10,20,30,40,50};
        int[] arrivals = {1,2,3,4,5};
        FCFS3 test_fcfs = new FCFS3(bursts, arrivals);

        System.out.println(test_fcfs.toString());
        
        //int[] strings = {"string1", "string2", "string3"};
        //int[] boosts = {40, 32, 34};

        //String[] sorted = IntStream.range(0, boosts.length).boxed()
         //   .sorted(Comparator.comparingInt(i -> boosts[i]))
         //   .map(i -> strings[i])
         //   .toArray(String[]::new);

        //for(int i = 0; i < sorted.length; i++){
        //}
    }
}
