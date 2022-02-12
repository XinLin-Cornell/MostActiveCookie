import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class most_active_cookie {
    public static final String delimiter= ",";

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.println("This is invalid command, please try again.");
                return;
            }

            String fileName= args[0];
            String queryDate= args[2];

            BufferedReader br= new BufferedReader(new FileReader(fileName));
            String line= br.readLine();
            String[] tempArr;

            // Using HashMap to keep track of day with cookie; key: day, value: (cookie,
            // frequency)
            HashMap<String, Map<String, Integer>> map= new HashMap<>();

            // read log from csv file
            while ((line= br.readLine()) != null) {
                tempArr= line.split(delimiter);
                String cookie= tempArr[0];
                int idx= tempArr[1].indexOf("T");
                String day= tempArr[1].substring(0, idx);

                // add key and value to hashmap
                map.putIfAbsent(day, new HashMap<>());
                int freq= map.get(day).getOrDefault(cookie, 0);
                map.get(day).put(cookie, freq + 1);
            }
            br.close();

            // check whether active cookie exist or not
            if (map.get(queryDate) == null) {
                System.out.println("There is no cookie for this specified day.");
                return;
            }

            // create max heap compare by cookie's occurence
            PriorityQueue<String> maxHeap= new PriorityQueue<>(
                new Comparator<>() {
                    public int compare(String o1, String o2) {
                        return map.get(queryDate).get(o2) - map.get(queryDate).get(o1);
                    }
                });

            // add all cookies associated with query day to max heap
            for (String cookie : map.get(queryDate).keySet()) {
                maxHeap.offer(cookie);
            }

            List<String> output= new ArrayList<>();

            String mostActiveCookie= maxHeap.poll();
            output.add(mostActiveCookie);
            int maxFreq= map.get(queryDate).get(mostActiveCookie);

            // if there exists multiple cookies meet the criteria, add to output
            while (maxHeap.peek() != null) {
                String cookie= maxHeap.poll();
                if (maxFreq == map.get(queryDate).get(cookie)) {
                    output.add(cookie);
                } else
                    break;
            }

            // print output
            for (String cookie : output) {
                System.out.println(cookie);
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }
}