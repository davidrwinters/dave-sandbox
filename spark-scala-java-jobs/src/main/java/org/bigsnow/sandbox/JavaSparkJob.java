package org.bigsnow.sandbox;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class JavaSparkJob {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger(JavaSparkJob.class);

		if (args.length < 2) {
			logger.error("=> wrong parameters number");
			System.err.println("Usage: JavaSparkJob <path-to-files> <output-path>");
			System.exit(1);
		}

		String jobName = "JavaSparkJob";
		SparkConf conf = new SparkConf().setAppName(jobName);
		JavaSparkContext sc = new JavaSparkContext(conf);

		String pathToFiles = args[0];
		String outputPath = args[1];

		logger.info("=> jobName \"" + jobName + "\"");
		logger.info("=> pathToFiles \"" + pathToFiles + "\"");

		JavaRDD<String> files = sc.textFile(pathToFiles);

		// do your work here
		JavaRDD<String> rowsWithoutSpaces = files.map(new Function<String, String>() {
			public String call(String s) { return s.replaceAll(" ",  ","); }
		});

		// and save the result
		rowsWithoutSpaces.saveAsTextFile(outputPath);
	}
}
