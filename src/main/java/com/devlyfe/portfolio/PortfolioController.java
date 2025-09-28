package com.devlyfe.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class PortfolioController {

    @Autowired
    private JavaMailSender mailSender;

    // ========================================
    // Home Page
    // ========================================
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("skills", getSkills());
        model.addAttribute("experiences", getExperiences());
        model.addAttribute("projects", getProjects()); // shared projects
        model.addAttribute("currentPage", "home");
        return "index"; // index.html
    }

    // ========================================
    // Projects Page
    // ========================================
    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", getProjects()); // shared projects
        model.addAttribute("currentPage", "projects");
        return "projects"; // projects.html
    }

    // ========================================
    // About Page
    // ========================================
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("currentPage", "about");
        model.addAttribute("aboutImage", "/image/hero.png"); // path to your About image
        return "about"; // about.html
    }

    // ========================================
    // Skills Page
    // ========================================
    @GetMapping("/skills")
    public String skills(Model model) {
        model.addAttribute("skills", getSkills());
        model.addAttribute("currentPage", "skills");
        return "skills"; // skills.html
    }

    // ========================================
    // Experience Page
    // ========================================
    @GetMapping("/experience")
    public String experience(Model model) {
        model.addAttribute("experiences", getExperiences());
        model.addAttribute("currentPage", "experience");
        return "experience"; // experience.html
    }

    // ========================================
    // Contact Page (GET)
    // ========================================
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("currentPage", "contact");
        return "contact"; // contact.html
    }

    // ========================================
    // Contact Form Submission (POST)
    // ========================================
    @PostMapping("/contact")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            Model model) {

        boolean mailSent = false;
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("s.stanhatarin@gmail.com"); // receiving email
            mailMessage.setSubject("Portfolio Contact Form");
            mailMessage.setText(message);
            mailMessage.setReplyTo(email);
            mailSender.send(mailMessage);
            mailSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("success", mailSent);
        model.addAttribute("currentPage", "contact");
        return "contact";
    }

    // ========================================
    // ========================================
    // Shared Data Methods
    // ========================================

    private List<Skill> getSkills() {
        return Arrays.asList(
            new Skill("Java/Spring Boot", 95),
            new Skill("React/JavaScript", 90),
            new Skill("UI/UX Design", 88),
            new Skill("Database Design", 85),
            new Skill("DevOps", 80),
            new Skill("Cloud Architecture", 85)
        );
    }

    private List<Experience> getExperiences() {
        return Arrays.asList(
            new Experience("Senior Full Stack Developer", "Tech Innovations Inc.", "2021-Present",
                "Led development of enterprise applications, improved system performance by 40%, mentored junior developers."),
            new Experience("Frontend Developer", "Digital Solutions LLC", "2019-2021",
                "Built responsive web applications, collaborated with UX team to implement designs, reduced load times by 30%."),
            new Experience("Software Engineer", "CodeCraft Studios", "2017-2019",
                "Developed RESTful APIs, implemented CI/CD pipelines, participated in code reviews.")
        );
    }

    private List<Project> getProjects() {
        return Arrays.asList(
            new Project("Task Management App", "/image/project2.png",
                "Collaborative project management tool with real-time updates",
                Arrays.asList("Vue.js", "Node.js", "MongoDB")),
            new Project("Weather Dashboard", "/image/project1.png",
                "Real-time weather application with forecasting capabilities",
                Arrays.asList("JavaScript", "API Integration", "CSS")),
            new Project("Portfolio Website", "/image/project2.png",
                "Responsive portfolio site with modern design",
                Arrays.asList("Spring Boot", "Thymeleaf", "Bootstrap"))
        );
    }

    // ========================================
    // Data Classes
    // ========================================
    public static class Skill {
        private String name;
        private int percentage;
        public Skill(String name, int percentage) { this.name = name; this.percentage = percentage; }
        public String getName() { return name; }
        public int getPercentage() { return percentage; }
    }

    public static class Experience {
        private String position;
        private String company;
        private String period;
        private String description;
        public Experience(String position, String company, String period, String description) {
            this.position = position; this.company = company; this.period = period; this.description = description;
        }
        public String getPosition() { return position; }
        public String getCompany() { return company; }
        public String getPeriod() { return period; }
        public String getDescription() { return description; }
    }

    public static class Project {
        private String title;
        private String imageUrl;
        private String description;
        private List<String> technologies;
        public Project(String title, String imageUrl, String description, List<String> technologies) {
            this.title = title; this.imageUrl = imageUrl; this.description = description; this.technologies = technologies;
        }
        public String getTitle() { return title; }
        public String getImageUrl() { return imageUrl; }
        public String getDescription() { return description; }
        public List<String> getTechnologies() { return technologies; }
    }
}