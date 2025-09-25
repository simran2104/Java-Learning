import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.stream.*;

class StreamsArray
{
	public static void main (String[] args)
	{
	    List<Integer> nums = Arrays.asList(1,2,2,2,2,2,12,56,23,9,10);
		
		// Filter Even Numbers
		nums.stream().filter(a->a%2==0).forEach(a -> System.out.print(a + " "));
		
		System.out.println();
		
		// Square Each Number
		nums.stream().map(a->a*a).forEach(a->System.out.print(a+" "));
		
		System.out.println();
		
		List<String> words = Arrays.asList("Pear", "Apple", "Chair", "ABC", "Banana", "Chair", "Apple", "Apple");
		
		// String to UpperCase
		words.stream().map(a->a.toUpperCase()).forEach(a->System.out.print(a + " "));
		
		System.out.println();
		
		// Count Strings longer than 4 Chars
		long count = words.stream().filter(a->a.length()>4).count();
		System.out.println("Count of Strings longer than 4 Chars " + count);
		
		// Find first element greater than 10
        nums.stream().filter(a->a>10).findFirst().ifPresent(a->System.out.println("First element greater than 10: " + a));
        
        // Sort list of Strings alphabetically
        words.stream().sorted().forEach(a->System.out.print(a + " "));
        
		System.out.println();
		
		// sum of List
		int sum = nums.stream().reduce(0, (a,b) -> a+b);
		System.out.println("Sum: "+ sum);

        // max ele in List
        nums.stream().max((a,b) -> a-b).ifPresentOrElse(
            a->System.out.println("Maximum Ele: " + a),
            () -> System.out.println("No maximum Element Present"));
        
        // Join list of strings
        String joined = words.stream().collect(Collectors.joining(" "));
        System.out.println("Joined String: "+ joined);
        
        // remove duplicates
        nums.stream().distinct().forEach(a->System.out.print(a + " "));
        
        System.out.println();
        
        // filter and sort in descending order
        nums.stream().filter(a->a>5).sorted((a,b) -> b-a).forEach(a->System.out.print(a + " "));
        
        System.out.println();
        
        // Find Average
        nums.stream().reduce((a,b)->a+b).ifPresent(
            a->System.out.println("Average of Ele: " + (a/nums.size())));
        
        nums.stream().mapToInt(a->a).average().ifPresent(
            a->System.out.println("Average of Ele using Int Stream: " + a));
            
        // Group Strings by length
        words.stream().collect(Collectors.groupingBy(a -> a.length())).forEach(
            (key, value)->System.out.print(key + ": " + value + ", "));
            
        System.out.println();
        
        // Count occurrences of Each Element
        words.stream().collect(Collectors.groupingBy(a -> a))
	}
}
