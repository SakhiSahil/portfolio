package com.devlyfe.portfolio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class PortfolioController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String home(Model model) {
        // Skills data
        List<Skill> skills = Arrays.asList(
            new Skill("Java/Spring Boot", 95),
            new Skill("React/JavaScript", 90),
            new Skill("UI/UX Design", 88),
            new Skill("Database Design", 85),
            new Skill("DevOps", 80),
            new Skill("Cloud Architecture", 85)
        );
        model.addAttribute("skills", skills);
        
        // Experience data
        List<Experience> experiences = Arrays.asList(
            new Experience("Senior Full Stack Developer", "Tech Innovations Inc.", "2021-Present", "Led development of enterprise applications, improved system performance by 40%, mentored junior developers."),
            new Experience("Frontend Developer", "Digital Solutions LLC", "2019-2021", "Built responsive web applications, collaborated with UX team to implement designs, reduced load times by 30%."),
            new Experience("Software Engineer", "CodeCraft Studios", "2017-2019", "Developed RESTful APIs, implemented CI/CD pipelines, participated in code reviews.")
        );
        model.addAttribute("experiences", experiences);
        
        // Projects data
        List<Project> projects = Arrays.asList(
            new Project("Task Management App", "/image/project2.png", "Collaborative project management tool with real-time updates", Arrays.asList("Vue.js", "Node.js", "MongoDB")),
            new Project("Weather Dashboard", "/image/project1.png", "Real-time weather application with forecasting capabilities", Arrays.asList("JavaScript", "API Integration", "CSS")),
            new Project("Portfolio Website", "/image/project2.png", "Responsive portfolio site with modern design", Arrays.asList("Spring Boot", "Thymeleaf", "Bootstrap"))
        );
        model.addAttribute("projects", projects);
        
        return "index";
    }

    @PostMapping("/contact")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            Model model) {
        boolean mailSent = false;
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("s.stanhatarin@gmail.com"); // Your receiving email
            mailMessage.setSubject("Portfolio Contact Form");
            mailMessage.setText(message); // Only the message field
            mailMessage.setFrom(email); // This will show as the sender if SMTP allows
            mailMessage.setReplyTo(email); // Always set reply-to
            mailSender.send(mailMessage);
            mailSent = true;
        } catch (Exception e) {
            e.printStackTrace();
            mailSent = false;
        }
        model.addAttribute("success", mailSent);
        model.addAttribute("currentPage", "contact");
        return "contact";
    }
    
    // Data classes
    public static class Skill {
        private String name;
        private int percentage;
        
        public Skill(String name, int percentage) {
            this.name = name;
            this.percentage = percentage;
        }
        
        public String getName() { return name; }
        public int getPercentage() { return percentage; }
    }
    
    public static class Experience {
        private String position;
        private String company;
        private String period;
        private String description;
        
        public Experience(String position, String company, String period, String description) {
            this.position = position;
            this.company = company;
            this.period = period;
            this.description = description;
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
            this.title = title;
            this.imageUrl = imageUrl;
            this.description = description;
            this.technologies = technologies;
        }
        
        public String getTitle() { return title; }
        public String getImageUrl() { return imageUrl; }
        public String getDescription() { return description; }
        public List<String> getTechnologies() { return technologies; }
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("currentPage", "about");
        return "about";
    }

    @GetMapping("/skills")
    public String skills(Model model) {
        List<Skill> skills = Arrays.asList(
            new Skill("Java/Spring Boot", 95),
            new Skill("React/JavaScript", 90),
            new Skill("UI/UX Design", 88),
            new Skill("Database Design", 85),
            new Skill("DevOps", 80),
            new Skill("Cloud Architecture", 85)
        );
        model.addAttribute("skills", skills);
        model.addAttribute("currentPage", "skills");
        return "skills";
    }

    @GetMapping("/experience")
    public String experience(Model model) {
        List<Experience> experiences = Arrays.asList(
            new Experience("Senior Full Stack Developer", "Tech Innovations Inc.", "2021-Present", "Led development of enterprise applications, improved system performance by 40%, mentored junior developers."),
            new Experience("Frontend Developer", "Digital Solutions LLC", "2019-2021", "Built responsive web applications, collaborated with UX team to implement designs, reduced load times by 30%."),
            new Experience("Software Engineer", "CodeCraft Studios", "2017-2019", "Developed RESTful APIs, implemented CI/CD pipelines, participated in code reviews.")
        );
        model.addAttribute("experiences", experiences);
        model.addAttribute("currentPage", "experience");
        return "experience";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        List<Project> projects = Arrays.asList(
            new Project("E-Commerce Platform", "https://via.placeholder.com/400x300", "Full-featured online store with payment processing and inventory management", Arrays.asList("React", "Spring Boot", "MySQL")),
            new Project("Task Management App", "https://via.placeholder.com/400x300", "Collaborative project management tool with real-time updates", Arrays.asList("Vue.js", "Node.js", "MongoDB")),
            new Project("Weather Dashboard", "https://via.placeholder.com/400x300", "Real-time weather application with forecasting capabilities", Arrays.asList("JavaScript", "API Integration", "CSS")),
            new Project("Portfolio Website", "https://via.placeholder.com/400x300", "Responsive portfolio site with modern design", Arrays.asList("Spring Boot", "Thymeleaf", "Bootstrap"))
        );
        model.addAttribute("projects", projects);
        model.addAttribute("currentPage", "projects");
        return "projects";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("currentPage", "contact");
        return "contact";
    }
}