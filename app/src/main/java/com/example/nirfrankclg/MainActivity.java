package com.example.nirfrankclg;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner domainSpinner;
    private EditText rankEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private CollegeAdapter collegeAdapter;
    private List<College> collegeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        domainSpinner = findViewById(R.id.domainSpinner);
        rankEditText = findViewById(R.id.rankEditText);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize College List
        collegeList = new ArrayList<>();

        // Adding some college data (both Engineering and Medical)
        loadColleges();

        // Set up College Adapter
        collegeAdapter = new CollegeAdapter(collegeList);
        recyclerView.setAdapter(collegeAdapter);

        // Set up Spinner for Domain selection
        ArrayAdapter<CharSequence> domainAdapter = ArrayAdapter.createFromResource(
                this, R.array.domain_options, android.R.layout.simple_spinner_item);
        domainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        domainSpinner.setAdapter(domainAdapter);

        domainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                filterColleges(); // Call the filter function when domain is selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle if nothing is selected
            }
        });

        // Set up the Search Button
        searchButton.setOnClickListener(v -> filterColleges());
    }

    // Load college data (both Medical and Engineering)
    private void loadColleges() {
        // Engineering Colleges
        collegeList.add(new College("Indian Institute of Technology Madras", 1, "Engineering", "IIT Madras is a premier institute for technical education in India, renowned for its high-quality research and teaching in various branches of engineering."));
        collegeList.add(new College("Indian Institute of Technology Delhi", 2, "Engineering", "IIT Delhi is one of the leading institutions in the country, known for its engineering, technology, and research programs."));
        collegeList.add(new College("Indian Institute of Technology Bombay", 3, "Engineering", "IIT Bombay offers world-class research and education in engineering, with a focus on innovation and entrepreneurship."));
        collegeList.add(new College("Indian Institute of Technology Kanpur", 4, "Engineering", "IIT Kanpur is known for its strong emphasis on research, technical education, and innovation in various engineering disciplines."));
        collegeList.add(new College("Indian Institute of Technology Kharagpur", 5, "Engineering", "IIT Kharagpur is the oldest IIT and offers a vast range of undergraduate, postgraduate, and doctoral programs in engineering and technology."));
        collegeList.add(new College("Indian Institute of Technology Roorkee", 6, "Engineering", "IIT Roorkee offers a wide range of engineering programs and is renowned for its contributions to research and technology."));
        collegeList.add(new College("Indian Institute of Technology Guwahati", 7, "Engineering", "IIT Guwahati is known for its strong emphasis on research and its diverse programs in engineering and technology."));
        collegeList.add(new College("National Institute of Technology Tiruchirappalli", 8, "Engineering", "NIT Trichy is one of the top NITs in India, offering undergraduate, postgraduate, and doctoral programs in engineering and technology."));
        collegeList.add(new College("National Institute of Technology Rourkela", 9, "Engineering", "NIT Rourkela is known for its strong academic programs in engineering and a highly reputable research output."));
        collegeList.add(new College("Indian Institute of Technology Hyderabad", 10, "Engineering", "IIT Hyderabad offers a world-class education in engineering, with a focus on innovation and research in technology fields."));

// Medical Colleges
        collegeList.add(new College("All India Institute of Medical Sciences (AIIMS) Delhi", 1, "Medical", "AIIMS Delhi is the top medical institute in India, providing world-class medical education, research, and healthcare services."));
        collegeList.add(new College("Post Graduate Institute of Medical Education and Research (PGIMER) Chandigarh", 2, "Medical", "PGIMER Chandigarh is a leading medical research institute, offering advanced medical education and high-quality healthcare services."));
        collegeList.add(new College("Christian Medical College (CMC) Vellore", 3, "Medical", "CMC Vellore is known for its outstanding medical education and services, with a focus on patient care and research."));
        collegeList.add(new College("King George’s Medical University (KGMU) Lucknow", 4, "Medical", "KGMU Lucknow is a prestigious institution that offers comprehensive medical education and high-quality healthcare services."));
        collegeList.add(new College("Banaras Hindu University (BHU) Varanasi", 5, "Medical", "BHU Varanasi offers one of the top medical programs in India, with a focus on research and patient care."));
        collegeList.add(new College("Jawaharlal Institute of Postgraduate Medical Education and Research (JIPMER) Puducherry", 6, "Medical", "JIPMER Puducherry is a leading institute for medical education and healthcare services, known for its research and academic excellence."));
        collegeList.add(new College("Amrita Institute of Medical Sciences (AIMS) Kochi", 7, "Medical", "AIMS Kochi is a renowned medical institution in India that provides top-notch healthcare and medical education."));
        collegeList.add(new College("Lady Hardinge Medical College (LHMC) New Delhi", 8, "Medical", "LHMC New Delhi is a top medical college offering quality medical education and healthcare services to the nation."));
        collegeList.add(new College("Madras Medical College (MMC) Chennai", 9, "Medical", "MMC Chennai is one of the oldest medical institutions in India, offering a wide range of medical programs and healthcare services."));
        collegeList.add(new College("Gandhi Medical College (GMC) Hyderabad", 10, "Medical", "GMC Hyderabad is known for its excellence in medical education, research, and providing high-quality healthcare to the community."));

    }

    // Filter the list based on the selected domain and entered rank
    private void filterColleges() {
        String selectedDomain = domainSpinner.getSelectedItem().toString();
        String rankInput = rankEditText.getText().toString();
        int rank = 0;

        if (!rankInput.isEmpty()) {
            try {
                rank = Integer.parseInt(rankInput); // Get the rank entered by the user
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter a valid rank", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Filter colleges based on domain and rank
        List<College> filteredList = new ArrayList<>();
        for (College college : collegeList) {
            boolean matchesDomain = college.getDomain().equalsIgnoreCase(selectedDomain);
            boolean matchesRank = rank == 0 || college.getnirfrank() == rank;

            if (matchesDomain && matchesRank) {
                filteredList.add(college);
            }
        }

        // Update the RecyclerView with the filtered list
        if (filteredList.isEmpty()) {
            Toast.makeText(MainActivity.this, "No colleges found for the selected criteria", Toast.LENGTH_SHORT).show();
        } else {
            collegeAdapter.updateCollegeList(filteredList);
        }
    }
}
