import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionModel
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.linalg.Vectors

// Load and parse the data
val data = sc.textFile("file:///home/biantao/train1222")
val parsedData = data.map { line =>
  val parts = line.split('\t')
  LabeledPoint(parts(1).toDouble, Vectors.dense(parts(0).toDouble))
}.cache()

// Building the model
val numIterations = 2000
val model = LinearRegressionWithSGD.train(parsedData, numIterations)

// Evaluate model on training examples and compute training error
val valuesAndPreds = parsedData.map { point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
}
val MSE = valuesAndPreds.map{case(v, p) => math.pow((v - p), 2)}.mean()
println("training Mean Squared Error = " + MSE)

val valuesAndPredsTest = sc.textFile("file:///home/biantao/test1222").map { line =>
  val parts = line.split('\t')
  LabeledPoint(parts(1).toDouble, Vectors.dense(parts(0).toDouble))
}.map{ point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
} 
val MSETest = valuesAndPredsTest.map{case(v, p) => math.pow((v - p), 2)}.mean()
println("training Mean Squared Error = " + MSETest)

// Save and load model
//model.save(sc, "myModelPath")
//val sameModel = LinearRegressionModel.load(sc, "myModelPath")

