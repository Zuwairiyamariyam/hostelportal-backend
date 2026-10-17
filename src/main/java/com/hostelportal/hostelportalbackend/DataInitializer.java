package com.hostelportal.hostelportalbackend;

import com.hostelportal.hostelportalbackend.entity.Admin;
import com.hostelportal.hostelportalbackend.entity.ComplaintCategory;
import com.hostelportal.hostelportalbackend.entity.Student;
import com.hostelportal.hostelportalbackend.repository.AdminRepository;
import com.hostelportal.hostelportalbackend.repository.ComplaintCategoryRepository;
import com.hostelportal.hostelportalbackend.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final ComplaintCategoryRepository categoryRepository;
    private final StudentRepository studentRepository;

    public DataInitializer(AdminRepository adminRepository,
                           ComplaintCategoryRepository categoryRepository,
                           StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Reset old records
        studentRepository.deleteAll();

        // 1. Admin Setup
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setAdminId("admin");
            admin.setPassword("admin123");
            adminRepository.save(admin);
        }

        // 2. Complaint Categories Setup
        if (categoryRepository.count() == 0) {
            String[] categories = {
                    "Room Maintenance", "Water Supply", "Food & Mess",
                    "Cleanliness / Dustbin", "Electrical Issue", "Safety & Ragging",
                    "Roommate Issue", "Warden & Staff", "General / Other"
            };
            for (String cat : categories) {
                ComplaintCategory c = new ComplaintCategory();
                c.setName(cat);
                c.setDescription(cat + " issues");
                categoryRepository.save(c);
            }
        }

        // 3. 96 Students Setup (32 Girls in LH, 64 Boys in BH)
        List<Student> list = new ArrayList<>();

        // --- LADIES HOSTEL (LH-101 to LH-108) ---
        // LH-101
        list.add(createStudent("Zuwairiya Mariyam R", "23CSE041", "23.03.2005", "CSE", 4, "LH-101", "FEMALE"));
        list.add(createStudent("Mirsha A", "23CSE030", "17.06.2004", "CSE", 4, "LH-101", "FEMALE"));
        list.add(createStudent("Kowsalya M", "23CSE019", "01.03.2005", "CSE", 4, "LH-101", "FEMALE"));
        list.add(createStudent("Kavya Darshini G", "23CSE015", "11.08.2004", "CSE", 4, "LH-101", "FEMALE"));

        // LH-102
        list.add(createStudent("Areeba S", "23AID051", "11.11.2005", "AI&DS", 4, "LH-102", "FEMALE"));
        list.add(createStudent("Afrin S", "23AID010", "12.08.2005", "AI&DS", 4, "LH-102", "FEMALE"));
        list.add(createStudent("Kayal G", "23ECE052", "04.11.2005", "ECE", 4, "LH-102", "FEMALE"));
        list.add(createStudent("Shreenithi V", "23ECE006", "29.12.2005", "ECE", 4, "LH-102", "FEMALE"));

        // LH-103
        list.add(createStudent("Sabeena M", "24CSE056", "24.04.2006", "CSE", 3, "LH-103", "FEMALE"));
        list.add(createStudent("Saranya S", "24CSE021", "25.07.2006", "CSE", 3, "LH-103", "FEMALE"));
        list.add(createStudent("Sandhiya K", "24AID007", "09.08.2006", "AI&DS", 3, "LH-103", "FEMALE"));
        list.add(createStudent("Keerthana M", "24AID039", "08.07.2006", "AI&DS", 3, "LH-103", "FEMALE"));

        // LH-104
        list.add(createStudent("Harini B", "24AID053", "03.02.2006", "AI&DS", 3, "LH-104", "FEMALE"));
        list.add(createStudent("Hema M", "24MEC016", "10.04.2006", "MECH", 3, "LH-104", "FEMALE"));
        list.add(createStudent("Nandhitha M", "24ECE016", "09.06.2006", "ECE", 3, "LH-104", "FEMALE"));
        list.add(createStudent("Nithya S", "24ECE008", "14.07.2006", "ECE", 3, "LH-104", "FEMALE"));

        // LH-105
        list.add(createStudent("Radha T", "24ECE027", "17.12.2006", "ECE", 3, "LH-105", "FEMALE"));
        list.add(createStudent("Sharjana Banu S", "25CSE011", "17.04.2006", "CSE", 2, "LH-105", "FEMALE"));
        list.add(createStudent("Kavitha G", "25CSE044", "21.08.2007", "CSE", 2, "LH-105", "FEMALE"));
        list.add(createStudent("Praveena", "25CSE006", "26.07.2007", "CSE", 2, "LH-105", "FEMALE"));

        // LH-106
        list.add(createStudent("Rifaya R", "25AID016", "30.01.2007", "AI&DS", 2, "LH-106", "FEMALE"));
        list.add(createStudent("Jeya Bharathi B", "25AID057", "01.08.2006", "AI&DS", 2, "LH-106", "FEMALE"));
        list.add(createStudent("Anisha B", "25AID034", "04.08.2007", "AI&DS", 2, "LH-106", "FEMALE"));
        list.add(createStudent("Nivetha M", "25MEC048", "17.03.2007", "MECH", 2, "LH-106", "FEMALE"));

        // LH-107
        list.add(createStudent("Shweta A", "25ECE009", "11.07.2007", "ECE", 2, "LH-107", "FEMALE"));
        list.add(createStudent("Bavana K", "25ECE048", "12.08.2007", "ECE", 2, "LH-107", "FEMALE"));
        list.add(createStudent("Sunitha S", "25ECE021", "17.11.2007", "ECE", 2, "LH-107", "FEMALE"));
        list.add(createStudent("Hrishika V", "25ECE056", "03.06.2007", "ECE", 2, "LH-107", "FEMALE"));

        // LH-108
        list.add(createStudent("Priya I", "26CSE053", "04.08.2008", "CSE", 1, "LH-108", "FEMALE"));
        list.add(createStudent("Malini Shree G", "26AID047", "27.06.2008", "AI&DS", 1, "LH-108", "FEMALE"));
        list.add(createStudent("Reena V", "26AID055", "12.12.2008", "AI&DS", 1, "LH-108", "FEMALE"));
        list.add(createStudent("Subathra K", "26ECE043", "26.09.2008", "ECE", 1, "LH-108", "FEMALE"));

        // --- GENTS HOSTEL (BH-101 to BH-116) ---
        // BH-101
        list.add(createStudent("Sakthi Harish S", "23CSE014", "08.03.2005", "CSE", 4, "BH-101", "MALE"));
        list.add(createStudent("Athithan R", "23CSE058", "12.02.2005", "CSE", 4, "BH-101", "MALE"));
        list.add(createStudent("Ajay G", "23AID024", "22.04.2005", "AI&DS", 4, "BH-101", "MALE"));
        list.add(createStudent("Pradip S", "23AID037", "06.06.2005", "AI&DS", 4, "BH-101", "MALE"));

        // BH-102
        list.add(createStudent("Naresh C", "23AID016", "06.05.2005", "AI&DS", 4, "BH-102", "MALE"));
        list.add(createStudent("Harish R", "23AID045", "05.04.2005", "AI&DS", 4, "BH-102", "MALE"));
        list.add(createStudent("Karthick Ram S", "23MEC038", "12.10.2005", "MECH", 4, "BH-102", "MALE"));
        list.add(createStudent("Muhsin M", "23MEC011", "28.12.2005", "MECH", 4, "BH-102", "MALE"));

        // BH-103
        list.add(createStudent("Mahendran K", "23MEC052", "14.07.2005", "MECH", 4, "BH-103", "MALE"));
        list.add(createStudent("Ravinesh A", "23MEC024", "09.06.2005", "MECH", 4, "BH-103", "MALE"));
        list.add(createStudent("Hameed M", "23MEC047", "27.05.2005", "MECH", 4, "BH-103", "MALE"));
        list.add(createStudent("Sanjay J", "23MEC006", "29.06.2005", "MECH", 4, "BH-103", "MALE"));

        // BH-104
        list.add(createStudent("Mustafa A", "23ECE038", "20.06.2005", "ECE", 4, "BH-104", "MALE"));
        list.add(createStudent("Ram Kumar P", "23ECE011", "09.08.2005", "ECE", 4, "BH-104", "MALE"));
        list.add(createStudent("Saarukesh A", "23ECE024", "16.09.2005", "ECE", 4, "BH-104", "MALE"));
        list.add(createStudent("Niranjan R", "23ECE047", "10.04.2005", "ECE", 4, "BH-104", "MALE"));

        // BH-105
        list.add(createStudent("Velayutha Krishnan R", "24CSE049", "26.10.2006", "CSE", 3, "BH-105", "MALE"));
        list.add(createStudent("Yokesh V", "24CSE013", "29.09.2006", "CSE", 3, "BH-105", "MALE"));
        list.add(createStudent("Thanveer Ahamed M", "24CSE032", "13.05.2006", "CSE", 3, "BH-105", "MALE"));
        list.add(createStudent("Hari B", "24CSE005", "02.08.2006", "CSE", 3, "BH-105", "MALE"));

        // BH-106
        list.add(createStudent("Saravanavel G", "24AID018", "30.07.2006", "AI&DS", 3, "BH-106", "MALE"));
        list.add(createStudent("Vishwa D", "24AID046", "14.01.2006", "AI&DS", 3, "BH-106", "MALE"));
        list.add(createStudent("Shyam B", "24AID025", "25.12.2006", "AI&DS", 3, "BH-106", "MALE"));
        list.add(createStudent("Surendhar S", "24MEC041", "06.08.2006", "MECH", 3, "BH-106", "MALE"));

        // BH-107
        list.add(createStudent("Nithish R", "24MEC053", "18.05.2006", "MECH", 3, "BH-107", "MALE"));
        list.add(createStudent("Gowtham R", "24MEC008", "15.06.2006", "MECH", 3, "BH-107", "MALE"));
        list.add(createStudent("Yuvraj B", "24MEC027", "23.02.2006", "MECH", 3, "BH-107", "MALE"));
        list.add(createStudent("Ganesh A", "24MEC045", "15.12.2006", "MECH", 3, "BH-107", "MALE"));

        // BH-108
        list.add(createStudent("Bala Saran K", "24ECE041", "23.03.2006", "ECE", 3, "BH-108", "MALE"));
        list.add(createStudent("Kishore H", "24ECE053", "06.04.2006", "ECE", 3, "BH-108", "MALE"));
        list.add(createStudent("Arun B", "24ECE045", "14.09.2006", "ECE", 3, "BH-108", "MALE"));
        list.add(createStudent("Ajith M", "25CSE038", "11.09.2007", "CSE", 2, "BH-108", "MALE"));

        // BH-109
        list.add(createStudent("Wassim M", "25CSE052", "11.01.2007", "CSE", 2, "BH-109", "MALE"));
        list.add(createStudent("Siddharth P", "25CSE027", "04.02.2007", "CSE", 2, "BH-109", "MALE"));
        list.add(createStudent("Akthar R", "25AID043", "26.08.2007", "AI&DS", 2, "BH-109", "MALE"));
        list.add(createStudent("Murali K", "25AID022", "08.06.2007", "AI&DS", 2, "BH-109", "MALE"));

        // BH-110
        list.add(createStudent("Rashid Khan S", "25AID009", "05.12.2007", "AI&DS", 2, "BH-110", "MALE"));
        list.add(createStudent("Santhosh K", "25MEC035", "06.10.2007", "MECH", 2, "BH-110", "MALE"));
        list.add(createStudent("Kavin S", "25MEC009", "13.05.2007", "MECH", 2, "BH-110", "MALE"));
        list.add(createStudent("Harshath K", "25MEC021", "22.08.2007", "MECH", 2, "BH-110", "MALE"));

        // BH-111
        list.add(createStudent("Sidharth M", "25MEC056", "03.12.2007", "MECH", 2, "BH-111", "MALE"));
        list.add(createStudent("Farhan S", "25MEC014", "14.11.2007", "MECH", 2, "BH-111", "MALE"));
        list.add(createStudent("Venkat P", "25ECE035", "15.02.2007", "ECE", 2, "BH-111", "MALE"));
        list.add(createStudent("Neeraj K", "25ECE014", "27.09.2007", "ECE", 2, "BH-111", "MALE"));

        // BH-112
        list.add(createStudent("Kalai A", "26CSE042", "26.11.2008", "CSE", 1, "BH-112", "MALE"));
        list.add(createStudent("Jeeva D", "26CSE017", "12.02.2008", "CSE", 1, "BH-112", "MALE"));
        list.add(createStudent("Zuhail R", "26CSE009", "21.05.2008", "CSE", 1, "BH-112", "MALE"));
        list.add(createStudent("Habeebullah", "26CSE036", "27.08.2008", "CSE", 1, "BH-112", "MALE"));

        // BH-113
        list.add(createStudent("Mani T", "26CSE024", "31.10.2008", "CSE", 1, "BH-113", "MALE"));
        list.add(createStudent("Anbu Selvan M", "26AID031", "14.08.2008", "AI&DS", 1, "BH-113", "MALE"));
        list.add(createStudent("Arul Ganesh G", "26AID008", "13.02.2008", "AI&DS", 1, "BH-113", "MALE"));
        list.add(createStudent("Aryan A", "26AID019", "10.05.2008", "AI&DS", 1, "BH-113", "MALE"));

        // BH-114
        list.add(createStudent("Arjun T", "26AID026", "22.01.2008", "AI&DS", 1, "BH-114", "MALE"));
        list.add(createStudent("Rajiv S", "26MEC043", "28.12.2008", "MECH", 1, "BH-114", "MALE"));
        list.add(createStudent("Riyasudeen I", "26MEC012", "01.01.2008", "MECH", 1, "BH-114", "MALE"));
        list.add(createStudent("Saravanan S", "26MEC057", "04.05.2008", "MECH", 1, "BH-114", "MALE"));

        // BH-115
        list.add(createStudent("Ashok S", "26MEC024", "22.11.2008", "MECH", 1, "BH-115", "MALE"));
        list.add(createStudent("Habeeb Rahuman S", "26MEC036", "21.09.2008", "MECH", 1, "BH-115", "MALE"));
        list.add(createStudent("Mohammed Irfan R", "26MEC008", "21.05.2008", "MECH", 1, "BH-115", "MALE"));
        list.add(createStudent("Aadini V", "26ECE012", "28.04.2008", "ECE", 1, "BH-115", "MALE"));

        // BH-116
        list.add(createStudent("Harsha S", "26ECE057", "22.01.2008", "ECE", 1, "BH-116", "MALE"));
        list.add(createStudent("Mohith M", "26ECE024", "07.06.2008", "ECE", 1, "BH-116", "MALE"));
        list.add(createStudent("Murugan S", "26ECE036", "31.05.2008", "ECE", 1, "BH-116", "MALE"));
        list.add(createStudent("Varshan M", "26ECE008", "20.12.2008", "ECE", 1, "BH-116", "MALE"));

        studentRepository.saveAll(list);
        System.out.println(">>> All 96 students uploaded into database successfully!");
    }

    private Student createStudent(String name, String rollNo, String dob, String dept, int year, String room, String gender) {
        Student s = new Student();
        s.setName(name);
        s.setRollNo(rollNo);
        s.setDob(dob);
        s.setDepartment(dept);
        s.setYear(year);
        s.setRoomNo(room);
        s.setGender(gender);
        s.setPhone("-");
        s.setStatus("ACTIVE");
        return s;
    }
}