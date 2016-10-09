package com.example.joanne.jsketch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton selectButton;
    ImageButton eraseButton;
    ImageButton rectButton;
    ImageButton circleButton;
    ImageButton lineButton;
    ImageButton paintButton;

    Button orangeButton;
    Button greenButton;
    Button redButton;

    ImageButton th1Button;
    ImageButton th2Button;
    ImageButton th3Button;

    Button importButton;
    final int IMPORT_PICTURE = 800;

    JCanvas view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            CUSTOM TOOLBAR
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /*
            Canvas view
         */

        view = (JCanvas) findViewById(R.id.canvas);

        /*
            TOOLS
         */
        selectButton = (ImageButton) findViewById(R.id.select);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.toolId = Model.SELECT_ID;
                updateToolSelection();
            }
        });

        rectButton = (ImageButton) findViewById(R.id.rectangle);
        rectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.focusedShape = null;
                Model.toolId = Model.RECT_ID;
                updateToolSelection();
            }
        });

        circleButton = (ImageButton) findViewById(R.id.circle);
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.focusedShape = null;
                Model.toolId = Model.CIR_ID;
                updateToolSelection();
            }
        });

        lineButton = (ImageButton) findViewById(R.id.line);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.focusedShape = null;
                Model.toolId = Model.LINE_ID;
                updateToolSelection();
            }
        });

        eraseButton = (ImageButton) findViewById(R.id.eraser);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.focusedShape = null;
                Model.toolId = Model.ERASE_ID;
                updateToolSelection();
            }
        });
        eraseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Model.shapes.clear();
                Model.focusedShape = null;

                view.invalidate();
                return false;
            }
        });

        paintButton = (ImageButton) findViewById(R.id.paint);
        paintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.focusedShape != null) {
                    Model.focusedShape.setSelected(false);
                }
                Model.focusedShape = null;
                Model.toolId = Model.PAINT_ID;
                updateToolSelection();
            }
        });

        orangeButton = (Button) findViewById(R.id.orange);
        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.color = Model.ORANGE;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setLineColor(Model.color);
                }

                updateColorSelection();
            }
        });

        greenButton = (Button) findViewById(R.id.green);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.color = Model.GREEN;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setLineColor(Model.color);
                }

                updateColorSelection();
            }
        });

        redButton = (Button) findViewById(R.id.red);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.color = Model.RED;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setLineColor(Model.color);
                }

                updateColorSelection();
            }
        });

        th1Button = (ImageButton) findViewById(R.id.th1);
        th1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.thickness = Model.TH1;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setThickness(Model.TH1);
                }

                updateLineSelection();
            }
        });

        th2Button = (ImageButton) findViewById(R.id.th2);
        th2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.thickness = Model.TH2;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setThickness(Model.TH2);
                }

                updateLineSelection();
            }
        });

        th3Button = (ImageButton) findViewById(R.id.th3);
        th3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.thickness = Model.TH3;
                if (Model.focusedShape != null) {
                    Model.focusedShape.setThickness(Model.TH3);
                }

                updateLineSelection();
            }
        });

        importButton = (Button) findViewById(R.id.image);
        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMPORT_PICTURE);
            }
        });

        updateToolSelection();
        updateLineSelection();
        updateColorSelection();

        /*
            CANVAS
         */
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                JShape shape;

                float x = motionEvent.getX();
                float y = motionEvent.getY();

                float deltaX;
                float deltaY;

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        switch(Model.toolId) {
                            case Model.RECT_ID:
                                Model.focusedShape = new JRectangle(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(), motionEvent.getY(), Model.color, Model.thickness);

                                Model.shapes.add(Model.focusedShape);
                                break;
                            case Model.CIR_ID:
                                Model.focusedShape = new JCircle(motionEvent.getX(), motionEvent.getY(), 0, Model.color, Model.thickness);

                                Model.shapes.add(Model.focusedShape);
                                break;
                            case Model.LINE_ID:
                                Model.focusedShape = new JLine(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(), motionEvent.getY(), Model.color, Model.thickness);

                                Model.shapes.add(Model.focusedShape);
                                break;
                            case Model.SELECT_ID:
                                if (Model.focusedShape != null) {
                                    Model.focusedShape.setSelected(false);
                                }

                                shape =  Model.selectShape(motionEvent.getX(), motionEvent.getY());
                                if (shape != null) {
                                    if (shape.getId() != JImage.ID) {
                                        Model.color = shape.getLineColor();
                                        Model.thickness = shape.getThickness();
                                    }
                                    updateLineSelection();
                                    updateColorSelection();
                                }
                                Model.focusedShape = shape;

                                if (shape != null) {
                                    Model.focusedShape.setSelected(true);
                                }

                                break;
                            case Model.PAINT_ID:
                                shape =  Model.selectShape(motionEvent.getX(), motionEvent.getY());

                                if (shape != null) {
                                    shape.setFillColor(Model.color);
                                }

                                break;
                            case Model.ERASE_ID:
                                shape = Model.selectShape(motionEvent.getX(), motionEvent.getY());

                                if (shape != null) {
                                    Model.shapes.remove(shape);
                                }
                                break;

                        }

                        break;
                    case MotionEvent.ACTION_MOVE:
                        switch(Model.toolId) {
                            case Model.RECT_ID:
                                if (Model.focusedShape != null) {
                                    ((JRectangle) Model.focusedShape).setX1(motionEvent.getX());
                                    ((JRectangle) Model.focusedShape).setY1(motionEvent.getY());
                                }
                                break;
                            case Model.CIR_ID:
                                // calculate the hypotenuse for the radius
                                if (Model.focusedShape != null) {
                                    float x1 = ((JCircle) Model.focusedShape).getCenterX();
                                    float y1 = ((JCircle) Model.focusedShape).getCenterY();

                                    float x2 = motionEvent.getX();
                                    float y2 = motionEvent.getY();

                                    float hypotenuse = (float) Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
                                    ((JCircle) Model.focusedShape).setRadius(hypotenuse);
                                }
                                break;
                            case Model.LINE_ID:
                                if (Model.focusedShape != null) {
                                    ((JLine) Model.focusedShape).setX1(motionEvent.getX());
                                    ((JLine) Model.focusedShape).setY1(motionEvent.getY());
                                }
                                break;
                            case Model.SELECT_ID:
                                if (Model.focusedShape != null) {
                                    switch(Model.focusedShape.getId()) {
                                        case JRectangle.ID:
                                            JRectangle rectangle = ((JRectangle)Model.focusedShape);

                                            deltaX = rectangle.getX0() - x;
                                            deltaY = rectangle.getY0() - y;

                                            rectangle.setX0(x);
                                            rectangle.setY0(y);

                                            rectangle.setX1(rectangle.getX1() - deltaX);
                                            rectangle.setY1(rectangle.getY1() - deltaY);
                                            break;
                                        case JCircle.ID:
                                            JCircle circle = ((JCircle) Model.focusedShape);

                                            circle.setCenterX(x);
                                            circle.setCenterY(y);
                                            break;
                                        case JLine.ID:
                                            JLine line = ((JLine)Model.focusedShape);

                                            deltaX = line.getX0() - x;
                                            deltaY = line.getY0() - y;

                                            line.setX0(x);
                                            line.setY0(y);

                                            line.setX1(line.getX1() - deltaX);
                                            line.setY1(line.getY1() - deltaY);
                                            break;
                                        case JImage.ID:
                                            JImage image = ((JImage)Model.focusedShape);

                                            deltaX = image.getX0() - x;
                                            deltaY = image.getY0() - y;

                                            image.setX0(x);
                                            image.setY0(y);

                                            image.setX1(image.getX1() - deltaX);
                                            image.setY1(image.getY1() - deltaY);
                                            break;
                                    }
                                }
                                break;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Model.toolId == Model.RECT_ID || Model.toolId == Model.CIR_ID || Model.toolId == Model.LINE_ID) {
                            Model.focusedShape = null;
                        }

                        break;
                }

                view.invalidate();
                return true;
            }
        });
    }

    void updateLineSelection() {
        if (Model.thickness == Model.TH1) {
            th1Button.setActivated(true);
            th2Button.setActivated(false);
            th3Button.setActivated(false);
        } else if (Model.thickness == Model.TH2) {
            th1Button.setActivated(false);
            th2Button.setActivated(true);
            th3Button.setActivated(false);
        } else if (Model.thickness == Model.TH3) {
            th1Button.setActivated(false);
            th2Button.setActivated(false);
            th3Button.setActivated(true);
        }

        view.invalidate();
    }

    void updateColorSelection() {
        if (Model.color == Model.ORANGE) {
            orangeButton.setActivated(true);
            greenButton.setActivated(false);
            redButton.setActivated(false);
        } else if (Model.color == Model.GREEN) {
            greenButton.setActivated(true);
            orangeButton.setActivated(false);
            redButton.setActivated(false);
        } else if (Model.color == Model.RED) {
            redButton.setActivated(true);
            orangeButton.setActivated(false);
            greenButton.setActivated(false);
        }

        view.invalidate();
    }

    void updateToolSelection() {
        if (Model.toolId == Model.SELECT_ID) {
            selectButton.setActivated(true);
            rectButton.setActivated(false);
            circleButton.setActivated(false);
            lineButton.setActivated(false);
            eraseButton.setActivated(false);
            paintButton.setActivated(false);
        } else if (Model.toolId == Model.RECT_ID) {
            rectButton.setActivated(true);
            selectButton.setActivated(false);
            circleButton.setActivated(false);
            lineButton.setActivated(false);
            eraseButton.setActivated(false);
            paintButton.setActivated(false);
        } else if (Model.toolId == Model.CIR_ID) {
            circleButton.setActivated(true);
            selectButton.setActivated(false);
            rectButton.setActivated(false);
            lineButton.setActivated(false);
            eraseButton.setActivated(false);
            paintButton.setActivated(false);
        } else if (Model.toolId == Model.LINE_ID) {
            lineButton.setActivated(true);
            selectButton.setActivated(false);
            rectButton.setActivated(false);
            circleButton.setActivated(false);
            eraseButton.setActivated(false);
            paintButton.setActivated(false);
        } else if (Model.toolId == Model.ERASE_ID) {
            eraseButton.setActivated(true);
            selectButton.setActivated(false);
            rectButton.setActivated(false);
            circleButton.setActivated(false);
            lineButton.setActivated(false);
            paintButton.setActivated(false);
        } else if (Model.toolId == Model.PAINT_ID) {
            paintButton.setActivated(true);
            selectButton.setActivated(false);
            rectButton.setActivated(false);
            circleButton.setActivated(false);
            lineButton.setActivated(false);
            eraseButton.setActivated(false);
        }

        view.invalidate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMPORT_PICTURE) {
                Uri uri = data.getData();
                if (uri != null) {
                    Bitmap bitmap;
                    JImage image;

                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        image = new JImage(bitmap);
                        Model.shapes.add(image);
                        view.invalidate();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
