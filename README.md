Norwegian-Turkish Dictionary

An open-source, educational Norwegian-Turkish dictionary Android application built with modern Java and Android SDK.

Features

- Fast Search: Instant lookup for Norwegian and Turkish words
- Simple Interface: Clean and user-friendly design  
- Offline Functionality: No internet connection required
- Educational Purpose: Perfect for language learners

Technical Stack

- Language: Java 21
- Android SDK: 33 (Tiramisu)
- Architecture: Simple MVC pattern
- Data Structure: HashMap for efficient word storage
- Serialization: Java Object Serialization

Installation

1. Clone the repository:
git clone https://github.com/yourusername/norwegian-turkish-dictionary.git

2. Open in Android Studio
3. Build and run on Android device/emulator (API 33+)

Data Format

Word Entry Format (words.txt):
turkish_word:norwegian_word
kitap:bok
kişi:person
kalem:blyant

Serialized Data:
- hashmap.dat contains pre-serialized HashMap for fast loading
- Generated from words.txt using the PC utility

Usage

1. Enter a word in the search box (Turkish or Norwegian)
2. Click "Find" button
3. View results showing word pairs
4. Use "Exit" to close the application

Contributing

We welcome contributions! Here's how you can help:

Adding New Words:
1. Edit words.txt in the format turkish:norwegian
2. Run the HashMap generator to update hashmap.dat
3. Submit a pull request

Algorithm Improvements:
- Feel free to experiment with different data structures
- Try alternative search algorithms  
- Optimize for performance or memory usage

Code Structure:
- Keep the code simple and educational
- Maintain clear documentation
- Follow Java best practices

License

This project is licensed under the GNU General Public License v3.0 - see the LICENSE file for details.

Educational Purpose

This project is designed for:
- Android development learners
- Data structure enthusiasts
- Language learning tool developers
- Open source contributors

Development Workflow

1. Word Management: Update words.txt with new entries
2. Data Generation: Use PC utility to regenerate hashmap.dat
3. Testing: Test search functionality and performance
4. Documentation: Update README with changes

Performance

- 20,000 words: ~20-50ms search time
- Efficient HashMap-based lookup
- Minimal memory footprint

Future Enhancements

- Favorite words functionality
- History of searched words  
- Voice pronunciation
- Quiz mode for learning
- Cloud synchronization
- Multiple dictionary support

Maintainer

[Your Name] - [Your GitHub Profile]

⭐ Star this repo if you find it helpful!
