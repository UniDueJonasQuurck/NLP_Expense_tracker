<h1> Shoptly - a mobile on-device receipt scanner using Googles MLKIT </h1>

Shoptly is an Android app for scanning your receipts and keeping track of your budget and expenses.

This app is able to scan receipts from grocery shopping or other purchases and detect its date, which is based on the devices date, store-name and total price. 
After dectection the user will be able to commit small changes to the detected values in case the OCR misinterpreted any values due to problems with the receipt itself.

Afterwards all collected and confirmed data will be stored in a database and then displayed in a list for the users overview and convienience. It also visualizes the collected data not only in a dynamic list, which is editable, but also in a linegraph for better visibility.

Besides that, the four most recent purchases will be displayed directly on the homefragment of the app, showing their price and where it was bought, it is also paired with a slider to set your monthly expense goal showing the percentage already spent.

Overall goal of this project is to create a viable, easy overview of expenses and thus financial literacy for the user. 

The apps recognition of natural language is based on googles MLKIT on device OCR recognition option.
In this stage of development this app is only suited for german receipts, as we coded this app to fit for this exact receipt format (fonts, structure).
Further patches could include the support for different languages, dateformats and currencies. But also features which let the user split bills among collegues, roommates or friends.

The app is written in Kotlin and should be easy to use and setup on every Android based device running on Android version 10. 
Pictures of the receipts can be taken directly in the application, but also imported from the local gallery for convienience.
Both of these options need a granted permission from the user!.

If there are any problems, bugs or crashes, feel free to contact us, so we can improve the expierence for you!

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

