package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr;

public class AutoGrader {

	// Test for bitwise operations (e.g., &, |, ^, ~, <<, >>)
	public boolean testBitwiseOperations(String filePath) throws IOException {
		System.out.println("Starting testBitwiseOperations with file: " + filePath);

		// Load participant's file
		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		// Parse the file using JavaParser
		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Variables to check conditions
		boolean hasBitwiseOperations = false;
		boolean hasShiftOperations = false;

		// 1. Checking Bitwise Operations (&, |, ^, ~)
		System.out.println("------ Checking Bitwise Operations ------");
		// Check for AND (&), OR (|), XOR (^)
		for (BinaryExpr expr : cu.findAll(BinaryExpr.class)) {
			if (expr.getOperator() == BinaryExpr.Operator.AND || expr.getOperator() == BinaryExpr.Operator.OR
					|| expr.getOperator() == BinaryExpr.Operator.XOR) {
				hasBitwiseOperations = true;
				System.out.println("✓ Found bitwise operation: " + expr.getOperator());
			}
		}

		// Check for NOT (~) operator
		for (UnaryExpr expr : cu.findAll(UnaryExpr.class)) {
			if (expr.getOperator() == UnaryExpr.Operator.BITWISE_COMPLEMENT) {
				hasBitwiseOperations = true;
				System.out.println("✓ Found bitwise NOT operation: ~");
			}
		}

		// Output the result for bitwise operations
		if (hasBitwiseOperations) {
			System.out.println("✓ Bitwise operations (&, |, ^, ~) are present.");
		} else {
			System.out.println("✘ Missing bitwise operations.");
		}

		// 2. Checking Shift Operations (<<, >>)
		System.out.println("------ Checking Shift Operations ------");
		// Check for left shift (<<) and right shift (>>)
		for (BinaryExpr expr : cu.findAll(BinaryExpr.class)) {
			if (expr.getOperator() == BinaryExpr.Operator.LEFT_SHIFT
					|| expr.getOperator() == BinaryExpr.Operator.SIGNED_RIGHT_SHIFT) {
				hasShiftOperations = true;
				System.out.println("✓ Found shift operation: " + expr.getOperator());
			}
		}

		// Output the result for shift operations
		if (hasShiftOperations) {
			System.out.println("✓ Shift operations (<<, >>) are present.");
		} else {
			System.out.println("✘ Missing shift operations.");
		}

		// Test result
		boolean result = hasBitwiseOperations && hasShiftOperations;
		System.out.println("Test result: " + result);

		return result;
	}
}
