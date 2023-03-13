ESPRESSO TESTS

Setup
1. Open the Android Studio IDE and import the project.
2. Ensure that the required dependencies are installed and up to date.
3. Run the application to make sure it builds and runs without errors.

Main Activity Test

Setup
1. To run the test cases, navigate to the MainActivityTest class and right-click on it.
2. Select "Run MainActivityTest" from the context menu.

Test Cases

testLaunchQuiz()
testLaunchAddEntry()
testLaunchAnswers()

These test cases verifies that the Quiz, Add Entry and Answers buttons
in the main activity launches it's respective activities.
The test cases uses Espresso's onView() and perform() methods to simulate a click. 
It then verifies that the correct activities is launched.

Quiz Activity Test

Setup
1. To run the test cases, navigate to the QuizActivityTest class and right-click on it.
2. Select "Run QuizActivityTest" from the context menu.

setUp()
This method initializes the test by launching the MainActivity 
and getting a reference to the ApplicationContext. 
It is annotated with @Before to ensure that it runs before the other tests.

testRandomButton()
This test case verifies that the core logic works by pressing the first button.
This is the correct answer 1/3 of the time and the wrong answer 2/3 of the time.
The test checks if the answer is right or wrong by checking the score
then checks wether the correct feedback is set to the corresponding String.
