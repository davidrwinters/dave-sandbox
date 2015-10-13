package org.bigsnow.sandbox

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.log4j.Logger

object ScalaSparkJob {

	def main(args: Array[String]) {

		var logger = Logger.getLogger(this.getClass())

		if (args.length < 2) {
			logger.error("=> wrong parameters number")
			System.err.println("Usage: ScalaSparkJob <path-to-files> <output-path>")
			System.exit(1)
		}

		val jobName = "ScalaSparkJob"
		val conf = new SparkConf().setAppName(jobName)
		val sc = new SparkContext(conf)

		val pathToFiles = args(0)
		val outputPath = args(1)

		logger.info("=> jobName \"" + jobName + "\"")
		logger.info("=> pathToFiles \"" + pathToFiles + "\"")

		val files = sc.textFile(pathToFiles)

		// do your work here
		val rowsWithoutSpaces = files.map(_.replaceAll(" ", ","))

		// and save the result
		rowsWithoutSpaces.saveAsTextFile(outputPath)
	}
}
