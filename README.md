# Automatic-Irrigation-System

I would like first to explain what I "UNDERSTAND" from the exercise

first, we need an automation system that tries to intervents human actions.
so the business logic that I understand is...

1- We have a sensor device that is responsible for irrigating the land plots
2- This sensor can NOT work simultaneously, and it gets busy when irrigating at most one land plot
3- Each land plot has its own time slot
4- What I understand from "time slot" is, it is just a schedule in which each day of the week has intervals in which the device should being irritating the land plot
with the given amount of water.
5- "Predict the (slots time / amount of water) based on the given type of agricultural crop / cultivated area", this bonus task statement was VERY AMBIGUOUS to me,
I do NOT really know how to logically predict the time slots/amount of water based on the give agricultural crop / cultivated area.
so what I did for the previous bonus task is that I used the Regression Machine Learning Algorithm to estimate/predict/guess the time slots/amount of water,
7- I made the system fully automated and does NOT require any human intevention, once the land of plot is configured (was given the time slots and amount of water),
the system Schedule the jobs to send an automated API calls to the sensor device (Mocking was used of course, because no real IOT API was given to me),
thus, retrying calls to the sensor device functionality become impossible, as well as the alerting system become usesless.
