# spark-scala-java-jobs
A sample project that builds Spark jobs that use Scala and Java.

## Build it

    mvn clean package

Inside the ``/target`` folder you will find the resulting fat jar called ``spark-scala-java-jobs-0.0.1-SNAPSHOT-jar-with-dependencies.jar``.

## Run it

In order to launch the Spark jobs use these commands in a shell with a configured Spark environment:

    spark-submit --class org.bigsnow.sandbox.ScalaSparkJob --master local[4] target/spark-scala-java-jobs-0.0.1-SNAPSHOT-jar-with-dependencies.jar data/ out/
    spark-submit --class org.bigsnow.sandbox.JavaSparkJob --master local[4] target/spark-scala-java-jobs-0.0.1-SNAPSHOT-jar-with-dependencies.jar data/ out/

The parameters (``data`` and ``out``) don't have to present the form ``hdfs://path/to/your/file`` but directly ``/path/to/your/files/`` because submitting a job to the default file system is HDFS.  To retrieve the merged result file locally:

    hadoop fs -getmerge out/ merged-file.txt
