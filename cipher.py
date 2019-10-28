import getopt
import sys
from sys import argv


def usage():
    print('Command:\tcipher.py -p -k')
    print('\t\t-p, --plaintext=')
    print('\t\t-k, --key=')


def encrypt(plain_text, encryption_key):
    padded_length = len(plain_text) + (len(plain_text) % len(encryption_key))
    padded_text = plain_text.ljust(padded_length, 'x')
    cipher_text = [[padded_text[int(i) + j*len(encryption_key) - 1]
                    for i in encryption_key] for j in range(0, int(len(padded_text)/len(encryption_key)))]
    return ''.join([''.join(cipher_text[i]) for i in range(0, len(cipher_text))])


def create_decryption_key(encryption_key):
    key = str()
    for i in range(1, len(encryption_key) + 1):
        for j in range(0, len(encryption_key)):
            if str(i) == encryption_key[j]:
                key += str(j+1)
    return key


if __name__ == '__main__':
    plaintext = str()
    key = str()
    try:
        opts, args = getopt.getopt(argv[1:], 'hp:k:', ['help', 'plaintext=', 'key='])
    except getopt.GetoptError:
        usage()
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            usage()
            sys.exit()
        elif opt in ("-p", "--plaintext"):
            plaintext = arg
        elif opt in ("-k", "--key"):
            key = arg
    print('Ciphertext: ' + encrypt(plaintext, key))
    print('Decryptionkey: ' + create_decryption_key(key))

