# OnlineTestApplication

Online Test System which contains three types of Data

-Audio.

-Video.

-Images.


The test consists on 3 portions.


-Image Test (image containing questions and Textual Answers).

-Audio Test (Audio questions and Textual Answers).

-Video Test (Video questions and Textual Answer)

<b>Details:</b>

Whenever a client connects to the server, it will display three kinds of tests (image, audio and video). The main server does not want to overburden itself by listening to all incoming clients and handle the tests, so it only stores the info about which sub-server contains what sort of test. When a sub-server connects to the main server it sends the main server a file containing the list of subjects of tests it has. When a client connects to a server it has to the choose the type of test and subject of test. The main server searches the records and sends back the appropriate test.



