# crypto-challenges
Java implementation of Cryptopals Cryptographic challenges

It all started at Matasano Security. The developed a teaching tool for
programming cryptography but it also served as a baiting hook to find people
that could do it properly.

The original challenges were only available though email. After sending an
email to cryptopals@matasano they would email back challenges to implement
in the programming langage of your choice. Once a set was done, you uploaded
them the code, and you would be given the next set of 8 challenges.

Those challenges are practical attacks against common vulnerabilities
in cryptography. Starting with basic string manipulation, XOR.. and learning
to break crypto.

After learning the basics (playing with hex, base64, XOR to build
ciphers and how to break them) you move on to block cipher cryptography
(padding, CBC mode, bitflipping attacks) to stream ciphers, and breaking
the Mersenne-Twister MT19937 generator.

The mathematical concepts are not that hard, most are 9th grade, but it
takes some work to move from them or pseudo code to actual code you wrote.
Doing the challenges not only teaches about cryptography and how to really
write code to break it, but also teaches basic data manipulation skills
which are extremly useful :

- string manipulation (ascii, utf, ranges, substrings)
- bitwise operations
- hashes and looking them up
- converting between strings and various number formats
- bit integer operations
- manipulation binary data (padding, packing, unpacking)
- searching for matching patterns
- client/server exchanges through sockets

Some problems are hard, some sets are hard, and sometimes some challenges
are just there to prepare for the next one more than being a challenge
themselves.

Several people have done those challenges. And they have published their
source code in C#, C++, and various other langages. But the whole point
of those challenges is doing them yourself because that's how you
learn. Instead of reading a paper about a flaw or bug that got exploited,
you write code that breaks stuff.

The author (authors ?) being the challenges did leave Matasano Security,
but those challenges keep going. They are not online instead of being
sent by email request, at the following address :

https://cryptopals.com/

There are now more sets than originally : from the 6 we now have 8 of them.
This makes a total of 48 challenges. They are derived from flaws that
were in real crypto-systems and modern cryptographic implementations.

Note that the last set of challenges (#8) is only available by email
request, and authors of the challenges do request that you do not
publish code for implementation to keep the challenge difficulty level
where it is (so you have to do it yourself and not look up for someone's
else code).

This repository will contain my own code as I go throught the challenges
myself. I have chosen Java because I like the language itself (weird huh!)
but also because I do believe those challenges are a very good path
to improve my own Java skillset which I would consider basic (learned it
but lacking serious practice on real-world implementations).

Might not be the best code, nor beautiful, but I will only commit
stuff that works and gets the job done, improving as I go by and learn
stuff.

What matters is doing the challenges, and doing them by yourself.
If some part of the code can help feel free to use any part of it.
Everything is put under BSD license. I would rather use "public domain"
but the country I am in does not allow it and considers an author the
only owner and even he/she can't put something in public domain
it happens "after enough time has passed" so I'm going to use a simple
BSD licence, which lets the code be free for all and at the same
time be compatible with the legal system of the country I am currently
in :-)

The challenges authors are :
- Thomas Ptacek
- Sean Devlin
- Alex Balducci
- Marcin Wielgoszewski

Once I have finished the whole sets (I hope I can reach the end of
them) I will start it again, and do it in C++ by choosing a modern
(post-2011) version of it.

I can be contacted at gilbert.fernandes@orange.fr
This adddress filters anything but GnuPG/PGP enciphered emails,
so you must use the public key at the root of this repository.
