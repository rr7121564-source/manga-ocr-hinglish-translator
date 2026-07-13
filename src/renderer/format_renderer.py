"""
Format Renderer: Display manga and webtoon in appropriate formats
"""

import logging
from typing import List, Dict
from pathlib import Path

logger = logging.getLogger(__name__)


class FormatRenderer:
    """Render manga/webtoon in appropriate reading format"""
    
    def __init__(self, output_dir: str = "output"):
        self.output_dir = output_dir
        Path(self.output_dir).mkdir(parents=True, exist_ok=True)
    
    def render_manga(self, image_paths: List[str], ocr_data: List[Dict] = None) -> Dict:
        """
        Render in traditional manga format (right-to-left, page-by-page)
        
        Args:
            image_paths: List of image paths
            ocr_data: Optional OCR data with text
            
        Returns:
            Manga rendering metadata
        """
        manga_data = {
            "format": "manga",
            "reading_direction": "right-to-left",
            "pages": []
        }
        
        for i, img_path in enumerate(image_paths):
            page_data = {
                "page_number": i + 1,
                "image_path": img_path,
                "text": ocr_data[i].get("hinglish", "") if ocr_data and i < len(ocr_data) else ""
            }
            manga_data["pages"].append(page_data)
        
        logger.info(f"Prepared {len(image_paths)} pages for manga format")
        return manga_data
    
    def render_webtoon(self, image_paths: List[str], ocr_data: List[Dict] = None) -> Dict:
        """
        Render in webtoon format (vertical scroll)
        
        Args:
            image_paths: List of image paths
            ocr_data: Optional OCR data with text
            
        Returns:
            Webtoon rendering metadata
        """
        webtoon_data = {
            "format": "webtoon",
            "reading_direction": "vertical-scroll",
            "panels": []
        }
        
        for i, img_path in enumerate(image_paths):
            panel_data = {
                "panel_number": i + 1,
                "image_path": img_path,
                "text": ocr_data[i].get("hinglish", "") if ocr_data and i < len(ocr_data) else ""
            }
            webtoon_data["panels"].append(panel_data)
        
        logger.info(f"Prepared {len(image_paths)} panels for webtoon format")
        return webtoon_data
    
    def create_html_viewer(self, content_data: Dict, filename: str = "viewer.html") -> str:
        """
        Create HTML viewer for the content
        
        Args:
            content_data: Manga or webtoon data
            filename: Output HTML filename
            
        Returns:
            Path to created HTML file
        """
        format_type = content_data.get("format", "unknown")
        
        if format_type == "manga":
            html = self._create_manga_html(content_data)
        elif format_type == "webtoon":
            html = self._create_webtoon_html(content_data)
        else:
            html = "<h1>Unknown format</h1>"
        
        output_path = Path(self.output_dir) / filename
        output_path.write_text(html)
        logger.info(f"Created HTML viewer at {output_path}")
        
        return str(output_path)
    
    def _create_manga_html(self, manga_data: Dict) -> str:
        """Create HTML for manga viewer"""
        html = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Manga Reader</title>
            <style>
                body { font-family: Arial, sans-serif; }
                .page { margin: 20px; padding: 20px; border: 1px solid #ccc; }
                .page img { max-width: 600px; }
                .text { margin-top: 10px; padding: 10px; background: #f0f0f0; }
                .nav { margin: 10px 0; }
            </style>
        </head>
        <body>
            <h1>Manga - Right to Left Reading</h1>
        """
        
        for page in manga_data.get("pages", []):
            html += f"""
            <div class="page">
                <h2>Page {page['page_number']}</h2>
                <img src="{page['image_path']}" alt="Page {page['page_number']}">
                <div class="text">
                    <strong>Hinglish:</strong> {page.get('text', 'N/A')}
                </div>
            </div>
            """
        
        html += """
        </body>
        </html>
        """
        return html
    
    def _create_webtoon_html(self, webtoon_data: Dict) -> str:
        """Create HTML for webtoon viewer"""
        html = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Webtoon Reader</title>
            <style>
                body { font-family: Arial, sans-serif; max-width: 500px; margin: 0 auto; }
                .panel { margin: 0; padding: 10px; border: 1px solid #ddd; }
                .panel img { width: 100%; height: auto; display: block; }
                .text { padding: 10px; background: #f9f9f9; }
            </style>
        </head>
        <body>
            <h1>Webtoon - Vertical Scroll</h1>
        """
        
        for panel in webtoon_data.get("panels", []):
            html += f"""
            <div class="panel">
                <img src="{panel['image_path']}" alt="Panel {panel['panel_number']}">
                <div class="text">
                    <strong>Hinglish:</strong> {panel.get('text', 'N/A')}
                </div>
            </div>
            """
        
        html += """
        </body>
        </html>
        """
        return html
