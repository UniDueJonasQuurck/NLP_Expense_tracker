<h1> Shoptly - a mobile on-device receipt scanner using MLKIT </h1>

Shoptly is an Android app for scanning receipts and keeping track of your budget and expenses.

This app is able to scan receipts from grocery shopping or other purchases and detect its date, store and total price. After dectection the user will be able to commit small changes to the detected values in case the OCR misinterpreted any values.

Afterwards all collected data will be stored in a database and then displayed for the users overview and convienience. It also visualizes the collected data not only in a dynamic list, but also in a linegraph for better visibility.

Besides that, the most recent purchases will be displayed directly on the homefragment of the app, paired with a slider to set your monthly expense goal showing the percentage spent.

Overall goal of this project is to create a viable, easy overview of expenses and financial literacy for the user. 

The apps recognition of natural language is based on googles MLKIT on device OCR recognition.
In this stage of development this app is only suited for german receipts, as we coded this app to fit for this exact receipt format (fonts, structure, dateformat)

It is written in Kotlin and should be easy to use on almost every Android based Device running Android Version 10 . 
Pictures of the receipts can be taken directly in the application, but also imported from local gallery for convienience.

Screenshots of app usage:

## Startup
![](https://user-images.githubusercontent.com/83307903/130647167-00944e51-322a-4f09-83e0-b57af543b9ce.gif)


## Homefragment
![](https://user-images.githubusercontent.com/83307903/130647295-c073ca7e-ea2f-48a2-bf48-2edef418ba60.png)






## Camerafragment
![](https://user-images.githubusercontent.com/83307903/130647449-f9bcf4a6-d927-461d-b696-662a41d5d3a4.png)


## Purchase History
![](https://user-images.githubusercontent.com/83307903/130647500-cc935748-3829-4dff-949f-33f830ca7d24.png)


## Graph
![](https://user-images.githubusercontent.com/83307903/130647409-9537ce28-87b4-4af2-b3fd-bdbf1119b47c.png)

