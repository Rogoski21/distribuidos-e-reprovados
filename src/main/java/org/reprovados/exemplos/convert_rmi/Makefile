all:			ConvertClient.class ConvertServer.class \
			Convert.class ConvertInterface.class

Convert.class:		Convert.java ConvertInterface.class
			@javac Convert.java

ConvertInterface.class:	ConvertInterface.java
			@javac ConvertInterface.java

ConvertClient.class:	ConvertClient.java
			@javac ConvertClient.java

ConvertServer.class:	ConvertServer.java
			@javac ConvertServer.java

clean:
			@rm -f *.class *~

