# OrderBook

Task:
The assignment is to produce executable code that will accept orders from standard input,
and to emit to standard output the trades as they occur. Once standard input ends, the
program should print the final contents of the order book.

Built on: Java 1.8

How to run?

Step 1:
Download the source code 

Step 2:
Go to directory where executable jar file reside
cd \order-book\out\artifacts\order_book_jar>

Step 3:
Run the jar with following command
java -jar .\order-book.jar

Note: If you want to try it with different inputs
Please edit the text file named test.txt
https://github.com/iderkhangai/OrderBook/blob/15f2659a8bb0a1f647e7c8be9370a45d715bd796/out/production/order-book/Resources/test.txt

Output:

trade 10006,10001,100,500
trade 10006,10002,100,10000
trade 10006,10004,103,100
trade 10006,10005,105,5400

	   Buyers		Sellers
	   50,000   99 | 105  14,600
	   25,500   98
