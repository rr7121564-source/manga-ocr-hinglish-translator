"""
Tests for format detector
"""

import unittest
from src.detector import FormatDetector


class TestFormatDetector(unittest.TestCase):
    """Test cases for FormatDetector"""
    
    def test_detector_initialization(self):
        """Test detector initialization"""
        detector = FormatDetector()
        self.assertEqual(detector.WEBTOON_RATIO_THRESHOLD, 1.5)
    
    def test_empty_image_list(self):
        """Test with empty image list"""
        result = FormatDetector.detect_format([])
        self.assertEqual(result, "unknown")
    
    def test_reading_direction_webtoon(self):
        """Test reading direction detection for webtoon"""
        direction = FormatDetector.detect_reading_direction([])
        self.assertIn(direction, ["left-to-right", "right-to-left", "vertical"])


if __name__ == "__main__":
    unittest.main()
