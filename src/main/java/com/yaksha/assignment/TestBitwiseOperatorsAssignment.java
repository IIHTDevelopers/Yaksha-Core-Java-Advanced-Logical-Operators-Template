package com.yaksha.assignment;

public class TestBitwiseOperatorsAssignment {

	public static void main(String[] args) {

		// Declare integer variables
		int a = 5; // 0101 in binary
		int b = 3; // 0011 in binary

		// 1. Perform bitwise operations: &, |, ^, ~, <<, >>
		int andResult = a & b; // Bitwise AND
		int orResult = a | b; // Bitwise OR
		int xorResult = a ^ b; // Bitwise XOR
		int notResultA = ~a; // Bitwise NOT
		int leftShiftResult = a << 2; // Left shift
		int rightShiftResult = a >> 1; // Right shift

		// Print the results of bitwise operations
		System.out.println("AND (a & b): " + andResult);
		System.out.println("OR (a | b): " + orResult);
		System.out.println("XOR (a ^ b): " + xorResult);
		System.out.println("NOT (~a): " + notResultA);
		System.out.println("Left Shift (a << 2): " + leftShiftResult);
		System.out.println("Right Shift (a >> 1): " + rightShiftResult);

		// Declare integer variables
		int x = 8; // 1000 in binary
		int y = 4; // 0100 in binary

		// 2. Combine bitwise operators in more complex expressions
		int complexResult = (x << 1) & y; // Shift x and then AND with y
		int negationResult = ~x; // Negate x

		// Print the results of the combined bitwise operations
		System.out.println("Complex Result ((x << 1) & y): " + complexResult);
		System.out.println("Negation Result (~x): " + negationResult);
	}
}
