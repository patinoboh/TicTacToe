# TicTacToe

## How to run

Firstly you need to download this repo into your local machine

### Downloading repository

This command downloads repository into you working directory

```bash
git clone https://github.com/patinoboh/TicTacToe.git
```

### Running the program

When you successfully downloaded repository, follow instructions how to run the actuall program

#### Compile

Please make sure you are in the **main folder** (that means folder TicTacToe)

```bash
javac -d bin/ src/tic/tac/toe/*.java
```

#### Server

```bash
java -cp bin/ src.tic.tac.toe.Program port playground_size number_of_stones_to_connect
```

If you do not want to specify this, just run

```bash
java -cp bin/ src.tic.tac.toe.Program 6666 3 3
```

#### Client

Everything for connecting to my (Patrik) running server is hardwired in the, so just submit the same command without the port argument

```bash
java -cp bin/ src.tic.tac.toe.Program 3 3
```
