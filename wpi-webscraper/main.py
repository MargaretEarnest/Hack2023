import requests
from bs4 import BeautifulSoup
import re
import csv

urlText = """
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1334", Office Assistant (Work Study Required)
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1599", Peer Tutor
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1409", jsonObjects.Student Proctor  FWS 22-23(Work Study required)
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=744", Clerk, Office assistant.
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1570", Uniform Custodian
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=777", BBT-Laboratory Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1567", Bioprocessing Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1091", Lab Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1564", Laboratory Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1565", Laboratory Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1566", Laboratory Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1184", Lab Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1587", Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1546", Peer Well-Being Ambassadors (Work-Study required)
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1381", ChE Website Coordinator
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=885", Chemistry Stockroom - FEDERAL WORKSTUDY
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=790", Laboratory Assistant, Work Study
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1433", Abby Kelly Foster Charter School, Educational Aide -- FWS Requir
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1422", African Community Education, After School Program Tutor -- FWS R
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1423", AVID Tutor, Worcester Public Schools -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1491", Communications Intern
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1470", Community Service Van Coordinator-- WPI SAO
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1488", Community Service Van Driver -- WPI SAO
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1490", Community Service Van Driver -- WPI SAO -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1494", Daytime Greeter 1, Mechanics Hall--FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1493", Daytime Greeter 2, Mechanics Hall--FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1495", Daytime Greeter 3, Mechanics Hall--FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1454", Front Desk jsonObjects.Student Staff-- Technocopia -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1426", Habitat for Humanity ReStore Volunteer Sales Associate -- FWS Re
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1485", Mechanics Hall -- Video Editor -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1427", Mentor, Exploradreams Robokids
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1482", Pakachoag Music School -- Communication Intern -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1481", Pakachoag Music School -- Volunteer Door Monitor -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1428", Shelter Assistant, Worcester Animal Rescue League -- FWS Require
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1429", Special Assistant to Executive Director, Greater Worcester Land
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1478", jsonObjects.Student Data Manager-- Net of Compassion -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1430", Tutor -- Elm Park Elementary School -- FWS Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1431", Various Internship Opportunities, Community Harvest Project -- F
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1593", AI Project Worker
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=897", Computer Scientist-Federal Work Study
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1335", Ed Psych &amp; Math Lab Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1293", Laboratory Assistant - Interaction Lab
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1577", Office Assistant 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1332", Additive Manufacturing Lab Assistant, PracticePoint
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1333", Machine Shop Assistant, PracticePoint 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1158", Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1596", Barista
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1597", Barista
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1362", GLOBAL ENGAGER-WEB DEVELOPER
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1292", Global Engagers-Media Assistants
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1553", GLOBAL LAB AUDIO SPECIALIST
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1554", GLOBAL LAB VIDEO SPECIALIST
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1438", archiving associate
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1373", Choir Librarian
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=767", Choral Division Webmaster
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1434", Music Technology Database / Website
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1271", Music Wiki Editor and Site Migration Liaison (Requires Federal W
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1558", Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1559", Theatre Technical Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1556", Innovation Studio Workshop Leader
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1446", Office Assistant, WPI Innovation Studio Office
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1386", jsonObjects.Student Building Manager, WPI Innovation Studio
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1588", Research Fellow
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1595", Assistant Photographer
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1590", MCL lab assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=889", office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1544", Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1510", Peer Learning Assistant and Graduate Learning Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1181", Manufacturing Labs Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1560", Office Assistant - Federal Work Study students only
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1484", programmer
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=771", Research Assistants in Surface Metrology
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=773", Assistant Supply Tech - Federal Work study required.
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1346", Public Affairs Office Assistant, Federal Work study required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1576", jsonObjects.Student Sustainability Intern
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1575", Sustainability Intern
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1113", Equipment Room Assistants
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1112", Event Workers
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1298", Men's Basketball jsonObjects.Student Manager
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1345", Office Assistant/jsonObjects.Student Manager- Softball
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1583", Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1370", Programer: Robotics; AI; Machine Learning; Physics; 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1380", Website Development and Management 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1549", jsonObjects.Student Office Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1594", Residential Services jsonObjects.Student Worker
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1523", Research Assistant 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1524", Research Assistant 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1586", Research assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1418", Learning Sciences Program Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1466", Psychological &amp; Cognitive Science Lab Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1467", Psychological and Cognitive Science Program Assistant
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1525", Research Assistant 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1363", Research Assistant- Learning Sciences Research 
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1419", HR Office Assistant (Federal Work Study Funds Required)
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1162", Alumni Relations Office Assistant - Federal Work Study Required
"/cgi-bin/student-jobs/showjob.cgi?job_type=A&amp;job_id=1578", jsonObjects.University Advancement Email Specialist"""

results = re.findall("(?<=\")(.*?)(?=\")", urlText)
jobs = []
csv_columns = []

for url in results:
    page = requests.get("https://web.wpi.edu/" + url)
    soup = BeautifulSoup(page.content, "html.parser")

    jobObject = {}
    for row in soup.find_all("tr"):
        columnName = row.find("th").text.replace(":", "")
        columnValue = row.find("td").text
        jobObject[columnName] = columnValue
        if columnName not in csv_columns:
            csv_columns.append(columnName)
    jobs.append(jobObject)

csv_file = "JobInfo.csv"
try:
    with open(csv_file, 'w', encoding="utf-8") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=csv_columns, lineterminator = '\n')
        writer.writeheader()
        for data in jobs:
            writer.writerow(data)
except IOError:
    print("I/O error")