all:			AdditionClient.class AdditionServer.class \
			Addition.class AdditionInterface.class

Addition.class:		Addition.java AdditionInterface.class
			@javac Addition.java

AdditionInterface.class:	AdditionInterface.java
			@javac AdditionInterface.java

AdditionClient.class:	AdditionClient.java
			@javac AdditionClient.java

AdditionServer.class:	AdditionServer.java
			@javac AdditionServer.java

clean:
			@rm -f *.class *~

