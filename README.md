🩺 Health Tracking App
A mobile app to help users track and visualize health metrics such as heart rate, blood pressure, sleep, water intake, and exercise. Built with Java, using Room DB, MPAndroidChart, and Material Design.

✨ Key Features
Home Page

View all health entries with detailed metrics.

Delete any entry via trash icon.

Add new entries with a "+" FAB.

View visualizations with a chart FAB.

Data Entry

Input fields for:

Heart Rate

Systolic/Diastolic Pressure

Sleep Duration & Quality

Water Intake

Exercise Duration & Type

On save, return to Home.

Charts & Insights

Visualize trends using:

📈 Line Charts (Heart Rate, Blood Pressure, Sleep Quality)

📊 Bar Charts (Sleep Duration, Water Intake, Exercise Duration)

🥧 Pie Chart (Exercise Type)

“No data to display” message when empty.

🛠️ Tech Stack
Language: Java

UI: Android XML, Material Design (FAB, CardView)

Database: Room (SQLite)

Architecture: MVVM (ViewModel, LiveData, Repository, DAO)

Charts: MPAndroidChart

🔄 Navigation Flow
Launch App → Home Page

Add Data → Tap "+" FAB → Fill Form → Save

View Charts → Tap chart FAB → View all metrics

📱 User Experience
Clean, modern UI

Smooth FAB-based navigation

Instant feedback for add/delete actions

Clear visual trends to monitor health

