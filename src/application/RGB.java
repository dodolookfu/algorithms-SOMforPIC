package application;

class Codebook
{
	int Or;
	int Og;
	int Ob;
	int x;
	int y;
	Codebook(int r,int g,int b,int x,int y)	{
		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.x = x;
		this.y = y;
	}
}


class RGB_group {
	int x;       // x
	int y;       // y
	int Or;      // ¬õ(initial)
	int Og;      // ºñ(initial)
	int Ob;      // ÂÅ(initial)
	int Nr;      // ¬õ(new)
	int Ng;      // ºñ(new)
	int Nb;      // ÂÅ(new)
	int group;   // ¸s
	RGB_group(int x, int y,int r,int g,int b,int group)
	{
		this.x = x;
		this.y = y;
		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.Nr = r;
		this.Ng = g;
		this.Nb = b;
		this.group = group;
	}
}

class RGB_app
{
	int color;
	int num;
	int app;
	int wa;
	RGB_app(int color,int num,int app,int wa)
	{
		this.color=color;
		this.num=num;
		this.app=app;
		this.wa=wa;
	}
}

class Color_info
{
	int num;
	int app;
	int color;
	Color_info(int num,int app,int color)
	{
		this.num=num;
		this.app=app;
		this.color=color;
	}
}

class NoRepeat_color
{
	int Or;      // ¬õ(¶}©l­È)
	int Og;      // ºñ(¶}©l­È)
	int Ob;      // ÂÅ(¶}©l­È)
	int num;
	int app;
	int group;
	NoRepeat_color(int r,int g,int b,int num,int app,int group)
	{
		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.num=num;
		this.app=app;
		this.group =group;
	}
}


