import random

INPUT_FILE_PATH = "Testing.txt"
OUTPUT_FILE_PATH = "outputfromfuzzer.txt"


#===== Mutation Operators =======
# Add in more mutation operators by adding new functions


def swap(line):
#     This function takes in a line and choose a random index
#     Then, swaps the letter at this index with the letter right after 
    string = ""
    if (len(line) > 1):
        index = random.randint(0, len(line)-2)   # ignore the last letter
        string = line[:index] + line[index+1] + line[index] + line[index+2:]
        print("swapped: " + line + " \tTO\t "+ string)
        return string
    else:
        # if only one letter, return without swapping
        print("swapped: "+ line)
        return line


def bitflip(line):
#     This function takes in a line
#     Then, flips a random bit of a random character in the input string
    string = ""
    letterindex = random.randint(0, len(line)-1)
    bitindex = random.randint(0, 6)
    random_letter = line[letterindex]
    # convert to binary
    binary = bin(ord(random_letter))[2:]    # get rid of '0b' in front
    if (len(binary) == 6):
        binary = "0" + binary
    if (binary[bitindex] == "1"):
        # flip 1 to 0
        binary = binary[:bitindex] + '0' + binary[bitindex+1:]
    elif (binary[bitindex] == "0"):
        # flip 0 to 1
        binary = binary[:bitindex] + '1' + binary[bitindex+1:]
    # convert binary back to character and insert to position
    string = line[:letterindex] + chr(int(binary,2)) + line[letterindex+1:]
    print("flipped: " + line + " \tTO\t "+ string)
    return string


def trim(line):
    string = ""
    if (len(line) > 1):
        index = random.randint(0, len(line)-2)
        string = line[:index]
        print("trimmed: " + line + " \tTO\t "+ string)
        return string
    else:
        # if only one letter, return without trimming
        print("trimmed: " + line + " \tTO\t "+ string)
        return line


# to add more mutations, add in the function above, and add the function into the MUTATIONS list
MUTATIONS = [swap, bitflip, trim]

# main function
# input: String, file path 
def generalised_fuzzer():
    file = open(INPUT_FILE_PATH)
    lines = file.read().splitlines()        # split by "\n"
    file.close()
    f = open(OUTPUT_FILE_PATH,"w+")
    for line in lines:
        output_line = random.choice(MUTATIONS)(line) # runs the random mutation functions
        f.write(output_line + "\n")
    f.close()


generalised_fuzzer()

# open the output file and print results
print("\n\nReading the output file")
file = open(OUTPUT_FILE_PATH)
lines = file.read().splitlines()
print(lines)