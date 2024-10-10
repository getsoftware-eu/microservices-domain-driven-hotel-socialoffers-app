# Project: Personalized Social Interaction for Hotel Guests using Big Data

## Epic Description:

As a hotel, we want to use big data to analyze guest preferences and interests, allowing us to offer personalized services. This will help guests connect with others who share similar interests and enhance their overall experience during their stay.

## Sprint 1 Goal: Implement Social Interests and Likes Processing
The goal of Sprint 1 is to establish the foundational functionality for guest social login and the processing of interests and likes. This will enable personalized social interactions and recommendations based on the gathered data.


### Epic:

As a customer (hotel guest) # I want to checkin to my hotel-feed # so I can attend to social interaction with other guests
A: The system should validate the hotel-entry-code ID entered by the guest.

# Product Backlog: 



    ### Split with acceptance criteria:
    As a guest on checkin page # I want to input my social-login and checkin-dates # so the app can make a social match with available hotel-offers or other guests interests.
    A: social button should weiterleiten to facebook Bestätigen und danach soll abfragen die checkin-dates
    A: Checkin Dates should be validated and not in the past
    A: guest should allow the usage of social data for the app
     


- As a hotel owner, I want to verify the GPS location of every guest, so I can ensure that only verified participants are part of the social interactions and maintain privacy for all guests.

- As a hotel guest, I want to post updates or comments on the hotel’s social feed, so that I can participate in social interactions with other guests.

- As a hotel-guest # I want to receive hotel-offers # so I can be aware of limited Angebote with low prices

- As a hotel-owner # I want to send individual offers to customer # because I want to уменьшить простой моих сотрудников

- As a hotel-owner # I want to see the social-interest-map of individual customers # so I can address my offers more точно

- As a hotel-owner # I want to stimulate my customers to offline communication in the hotel bar # because I want that customers spend more money inside of hotel

- As a hotel-guest # I want receive hotel-notifications about new guests with similar interests # because I value the hobby c другими с общими ценностями
  - A: Each guest's name, should be only part-displayed.


#### 1. User Story: Social Login via LinkedIn and Facebook
##### Description: 
    As a hotel guest, I want to log into the hotel’s social platform using my LinkedIn or Facebook account, so that I can easily sign in without creating a new account.
##### Acceptance Criteria:
   Guests can choose to log in via LinkedIn or Facebook on the registration page.
   The system fetches necessary profile data (e.g., name, profile picture) from the selected social platform.
   If the social login is successful, the guest is redirected to the check-in page.
   Error messages are displayed if the social login fails or is denied by the user.
   - Story Points: 5
   - Priority: High

#### 2. User Story: Interest and Likes Data Integration from LinkedIn and Facebook
##### Description: 
    As a hotel guest, I want the app to use my likes and interests from LinkedIn or Facebook to provide personalized social interactions and recommendations, so I can easily connect with others who share similar interests.
##### Acceptance Criteria:
   Upon social login, the system accesses and imports likes, interests, or relevant professional data from LinkedIn or Facebook (with guest consent).
   The system uses this data to recommend events, activities, or other guests with similar interests.
   The guest can view and update their preferences manually if needed.
   Data is securely handled and only used for relevant, personalized suggestions.
   - Story Points: 8
   - Priority: High

#### 3. User Story: Personalized Event and Service Suggestions Based on Social Data
##### Description: 
    As a hotel guest, I want to receive personalized event and service recommendations based on my LinkedIn or Facebook interests, so I can engage in relevant activities during my stay.
##### Acceptance Criteria:
   After social login, the system generates event and service suggestions based on the guest’s social media likes and interests.
   Suggestions include relevant networking events (LinkedIn) or social gatherings (Facebook).
   Guests receive notifications about upcoming events tailored to their imported preferences.
   The recommendations adjust dynamically as more data is gathered on the guest’s interactions.
   - Story Points: 5
   - Priority: Medium


#### 4. Task: Data Privacy and Consent Management
##### Description: 
    Ensure that user consent is obtained before accessing their social media data and that privacy regulations are followed.
##### Acceptance Criteria:
   Guests are asked for explicit consent to access their likes and interests from LinkedIn or Facebook.
   The system offers an option for guests to opt-out of social media data usage at any time.
   All guest data is stored securely and in compliance with GDPR or other relevant regulations.
   - Story Points: 3
   - Priority: High

# Sprint 1 Scope Overview 
- Total Story Points: 38
- High-Priority Stories: Focus on social login and likes/interests processing.
- Medium-Priority Stories: Focus on personalizing recommendations.

# Definition of Done for (Backlog) Sprint 1:
All registration, verification, and check-in features are fully functional (API tests, web slice).

The database correctly stores and retrieves guest details (DB slice tests)

The UI for guest registration and check-in is user-friendly and responsive.(Ui test, selenium)

All acceptance criteria for stories are met, with Junit and integration testing complete.

This sprint focuses on enabling core functionality for guest interaction, laying the groundwork for future features like viewing other guests and posting on the social feed.

# Sprint Backlog (Tasks)

### User Story: "Social Authorization"
#### Tasks:
 - Set up integration with the Facebook API - (5 StoryPoints)
 - Set up integration with the LinkedIn API - (5 StoryPoints)
 - Testing and error handling - (3 StoryPoints)

### User Story: "Import of Interest and Likes Data"
#### Tasks:
- Request permissions for data from social networks  - (8 StoryPoints)
- Saving data about likes and interests - (5 StoryPoints)


### User Story: "Personalized Recommendations"
#### Tasks:
- Algorithm for recommendations - (8 StoryPoints)
- Displaying recommendations and notifications - (5 StoryPoints)


# Sprint Review Meeting:
## Participants:
- Product Owner
- Dev Team
- Scrum Master
- Stakeholders

### Review Agenda:
Demonstration of Completed Features:

The Dev Team demonstrated the social login process, including successful login via LinkedIn and Facebook.

The process of fetching likes and interests from social media accounts was showcased, along with how it impacts personalized recommendations.

Feedback Collection:

Stakeholders provided positive feedback on the ease of use of the social login feature.
Guests appreciated the personalized recommendations and the clarity of the UI.
Suggestions were made to enhance the profile management interface further, including the ability to view suggested connections based on interests.

Discuss Any Challenges:

The Dev Team encountered some challenges with API rate limits during testing but managed to implement retry logic to mitigate this.
There was a brief discussion on improving the error messages for failed data fetch requests.



## Sprint 1 Summary:
Total Story Points Committed: 38


Total Story Points Completed: 38

Sprint Velocity: 38 points


#### User Stories Completed:

- Social Login via LinkedIn and Facebook

- Guests can now log in using their LinkedIn or Facebook accounts, streamlining the registration process.

- The login feature is tested and functioning correctly, with error handling for failed logins.

- Fetch Likes and Interests from Social Media

- The system successfully accesses and imports guests’ likes and interests from their social media accounts.

- Guests can view and modify their imported interests in their profiles.

- Personalization of Recommendations Based on Interests

- Personalized event and activity recommendations based on guests’ likes are generated and displayed on their dashboards.

- Guests receive notifications about events relevant to their interests.

- API Integration for Social Media Data Fetching

- Integration with LinkedIn and Facebook APIs is complete, enabling secure data fetching.

- Proper error handling and compliance measures are implemented to ensure user data security.

- User Interface for Social Login and Interest Management

- The UI for social login and displaying interests is user-friendly and visually appealing.

- Guests can easily navigate the interface to manage their social login options.

- Data Privacy and Consent Management

- User consent is clearly requested before accessing their social media data.

- The system provides options for guests to opt-out of social media data usage.

# Sprint Retrospective: 

### What Went Well:
Successful integration of social media login enhanced guest registration.

The team effectively communicated and collaborated, leading to a smooth development process.

All stories were completed on time.
### What Could Be Improved:
Allocate more time for testing API interactions to avoid rate limit issues.

Enhance the UI design process by involving user feedback earlier.
### Action Items for Next Sprint:
Improve API Rate Limiting Handling: Investigate alternative strategies to manage API usage limits more effectively.

UI/UX Improvements: Gather user feedback on the interface to refine the user experience further.

Preparation for Next Sprint: Start planning for additional features, such as guest networking suggestions and expanded event recommendations.

#### Next Steps:
Sprint Planning for Sprint 2: The team will meet to outline the next set of user stories, focusing on enhancing personalized recommendations and introducing guest networking features.

Feedback Implementation: Incorporate stakeholder feedback and suggestions into future development cycles.

By successfully completing Sprint 1, the team has laid a solid foundation for personalized social interactions among guests, driving engagement and enhancing the overall guest experience at the hotel.
