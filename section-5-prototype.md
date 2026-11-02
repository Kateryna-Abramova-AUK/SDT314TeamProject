# 5. Prototype

## 5.1 LessonPlan (Surfing and Training)

Steps 1 and 2 are unchanged from the main document. Step 3 is the complete file:
`src/LessonPlanDemo.java`

## 5.2 MessageTemplate (Customer Service)

### Step 1: Purpose and Design Improvement

**Why this pattern.** Customer Service sends the same kinds of message every day: booking confirmations, weather changes and reminders. Tidebreaker's friendly tone is part of the brand, so these messages should start from approved wording, and agents still need to add a note for an individual customer.

**Problem in the original code.** Each agent writes messages from scratch or changes a shared template directly:

```java
// original design
MessageTemplate weather = new MessageTemplate("Change of plans", "Dear customer,", "Regards");
weather.addLine("Your lesson is cancelled.");      // every agent words this differently

MessageTemplate forBen = sharedWeatherTemplate;    // not a copy, the same object
forBen.addLine("Your deposit carries over.");      // now every later customer gets Ben's note
```

The tone drifts from agent to agent, and a note meant for one customer can reach everyone who receives that template afterwards.

**How the pattern solves it.** `MessageTemplate` implements `Cloneable` (a second prototype style, different from the copy constructor in 5.1). `clone()` copies the object and then gives the copy its own list of lines. A `MessageLibrary` keeps the approved templates, and `create(key)` returns a fresh copy, so an agent can personalize it without touching the approved wording.

| Aspect | Before | After |
| --- | --- | --- |
| Maintainability | The wording is retyped by each agent | One approved template per message type |
| Flexibility | A personal note means changing the shared object or starting again | Copy, then add only the note |
| Reusability / organization | Edits leak into later messages | Each message owns an independent copy |

### Step 2: Scenario

- Agents send booking confirmations and weather-change notices from the front desk and the phone desk.
- Every message needs the same greeting and sign-off, plus the customer's own name, service and time slot.
- An agent can add a note for one customer, and that note must not reach anyone else.
- **Role of the pattern:** clone an approved template for each message instead of rewriting or sharing it.

### Step 3: Java Code

The complete file is `src/MessageTemplateDemo.java`.
