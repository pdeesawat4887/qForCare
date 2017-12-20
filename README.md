# qForCare

<p>Demo Android Application.</p>
<p>Used for booking queues in government hospitals.</p>

> # **Why we use qForCare ?**

- Notification when your queue arrived.
- Use Network Time Protocol (NTP) for booking.
- Separate queue into 2 priority.
- Manage priority for Application and Walk-in booking.
- Limit distance for application booking.

![ic_launcher2-web](https://user-images.githubusercontent.com/31108772/34218862-00b3d1aa-e5e2-11e7-85b9-ed2623e3a0b9.png)

## Users

- Nurse
- Patient ( already register )

## Functions

- Authentication with Citizen ID. ( Thailand Only )
- Stay Login when close application.
- Logout.

### Patient

- Booking queue.
- Limit distance from patient and hospital.
- Notification when your queue arrived.

### Nurse

- Walk-in or Outpatient booking queue.
- Booking queue as patient.
- Set distance from patient and hospital.
- Reset queue anytime.
- Call queue when doctor room empty, depend on cure.
- See remain queue in system.

## My Equipments 
- Android Studio Version 3.0
- NoSQL Database [FIREBASE CONSOLE](https://console.firebase.google.com/)
- NTP [Apache-commons-net](https://commons.apache.org/proper/commons-net/download_net.cgi)
- Icon from [FLATICON](https://www.flaticon.com)

## Testing

> Black-box testing

We test some module that sensitive with application 
- `GetTime.java` as `testGetTime.java`
<p> So we test this module because if user or patient cannot get correct time from NTP or NTP server change state to down, it harmful with database and cannot booking queue for user. We cannot expect output when NTP doesn't work.

- `CheckTimeBox` as `testCheckTimeBox.java`<br>

It from `GetTime.java` when user get correct time, application will separate booking into `SLOT` or `TIME-BOX` , we already create 10 time-box and specify 30 mins per time-box, start at `6 a.m.` and closing at `10 a.m.`. We want to make sure that hour and min that user get can define correct `time-box` because in database we already set `time-box` and cannot change. 

> We use scrum principle in agile to create and manage project.
