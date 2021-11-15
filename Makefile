.PHONY: clean run
run:
	javac Client.java && mv *.class tmp && cd tmp && java Client && cd ..
clean:
	cd tmp && rm * && cd ..