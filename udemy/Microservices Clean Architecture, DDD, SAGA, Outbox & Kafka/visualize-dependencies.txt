 Visualize dependencies:
 https://github.com/ferstl/depgraph-maven-plugin
 mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"